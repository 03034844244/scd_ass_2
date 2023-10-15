import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private List<Post> posts;
    private List<User> followers;
    private List<User> following;

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.posts = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public Post createPost(String text) {
        Post post = new Post(text, this);
        posts.add(post);
        return post;
    }

    public boolean deletePost(Post post) {
        if (posts.contains(post)) {
            posts.remove(post);
            return true;
        }
        return false;
    }

    public boolean follow(User user) {
        if (!following.contains(user)) {
            following.add(user);
            user.followers.add(this);
            return true;
        }
        return false;
    }

    public boolean unfollow(User user) {
        if (following.contains(user)) {
            following.remove(user);
            user.followers.remove(this);
            return true;
        }
        return false;
    }
    
    public String toString() {
        return "User ID: " + id + ", Username: " + username + ", Email: " + email;
    }
}

class Post {
    private int id;
    private String text;
    private Date datePosted;
    private User author;
    private List<Comment> comments;

    public Post(String text, User author) {
        this.id = new Random().nextInt(1000);
        this.text = text;
        this.datePosted = new Date();
        this.author = author;
        this.comments = new ArrayList<>();
    }

    public Comment addComment(String text, User author) {
        Comment comment = new Comment(text, author);
        comments.add(comment);
        return comment;
    }

    public boolean deleteComment(Comment comment) {
        if (comments.contains(comment)) {
            comments.remove(comment);
            return true;
        }
        return false;
    }
    
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(datePosted);
        return "Post ID: " + id + ", Author: " + author.getUsername() + ", Date: " + dateString + "\n" + text;
    }
}

class Comment {
    private int id;
    private String text;
    private Date datePosted;
    private User author;

    public Comment(String text, User author) {
        this.id = new Random().nextInt(1000);
        this.text = text;
        this.datePosted = new Date();
        this.author = author;
    }
    
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(datePosted);
        return "Comment ID: " + id + ", Author: " + author.getUsername() + ", Date: " + dateString + "\n" + text;
    }
}

public class SocialNetworkApp {
    private static final String USERS_FILE = "users.txt";
    private static final String POSTS_FILE = "posts.txt";
    private static final String COMMENTS_FILE = "comments.txt";
    
    private List<User> users;
    private List<Post> posts;
    private List<Comment> comments;
    
    public SocialNetworkApp() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
        comments = new ArrayList<>();
    }
    
    public void loadUsers() {
        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String username = parts[1];
                String password = parts[2];
                String email = parts[3];
                User user = new User(id, username, password, email);
                users.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
    
    public void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.println(user.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadPosts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(POSTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\n");
                int id = Integer.parseInt(parts[0].substring(8));
                String postText = parts[1];
                int authorId = Integer.parseInt(parts[2].substring(10));
                User author = findUserById(authorId);
                Post post = new Post(postText, author);
                post.setId(id);
                posts.add(post);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void savePosts() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(POSTS_FILE))) {
            for (Post post : posts) {
                writer.println(post.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadComments() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COMMENTS_FILE)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\n");
                int id = Integer.parseInt(parts[0].substring(11));
                String commentText = parts[1];
                int authorId = Integer.parseInt(parts[2].substring(13));
                int postId = Integer.parseInt(parts[3].substring(10));
                User author = findUserById(authorId);
                Post post = findPostById(postId);
                Comment comment = new Comment(commentText, author);
                comment.setId(id);
                post.addComment(comment);
                comments.add(comment);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void saveComments() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COMMENTS_FILE))) {
            for (Comment comment : comments) {
                writer.println(comment.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public User findUserById(int userId) {
        for (User user : users) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }
    
    public Post findPostById(int postId) {
        for (Post post : posts) {
            if (post.getId() == postId) {
                return post;
            }
        }
        return null;
    }

       

        public void run() {
            loadUsers();
            loadPosts();
            loadComments();
    
            User user1 = new User(1, "user1", "password1", "user1@email.com");
            User user2 = new User(2, "user2", "password2", "user2@email.com");
            
            users.add(user1);
            users.add(user2);
            
            saveUsers();
    
            user1.createPost("Hello, this is my first post!");
            user1.createPost("Second post from user1");
            user2.createPost("First post from user2");
    
            user1.follow(user2);
    
            Post post1 = user2.createPost("User2's post");
            user1.createPost("Another post from user1");
    
            user2.unfollow(user1);
    
            Comment comment1 = post1.addComment("Nice post, user2!", user1);
            Comment comment2 = post1.addComment("Thank you!", user2);
    
            savePosts();
            saveComments();
        }
    
        public static void main(String[] args) {
            SocialNetworkApp app = new SocialNetworkApp();
            app.run();
        
            // a. Create a user
            User newUser = new User(3, "user3", "password3", "user3@email.com");
            app.users.add(newUser);
            app.saveUsers();
        
            // b. Create a Post
            User user1 = app.findUserById(1); // Assuming user with ID 1 exists
            if (user1 != null) {
                Post newPost = user1.createPost("This is a new post from user1.");
                app.posts.add(newPost);
                app.savePosts();
            }
        
            // c. Comment on post
            User user2 = app.findUserById(2); // Assuming user with ID 2 exists
            Post post2 = app.findPostById(2); // Assuming post with ID 2 exists
            if (user2 != null && post2 != null) {
                Comment newComment = post2.addComment("A new comment from user2.", user2);
                app.comments.add(newComment);
                app.saveComments();
            }
        
            // d. Delete post
            Post post3 = app.findPostById(3); // Assuming post with ID 3 exists
            if (post3 != null) {
                boolean deleted = user1.deletePost(post3);
                if (deleted) {
                    app.posts.remove(post3);
                    app.savePosts();
                }
            }
        
            // e. Exception handling use-cases
            try {
                // Attempt to access a non-existent user
                User nonExistentUser = app.findUserById(10);
                if (nonExistentUser == null) {
                    throw new IllegalArgumentException("User not found.");
                }
        
                // Attempt to delete a non-existent post
                Post nonExistentPost = new Post("This post doesn't exist.", user1);
                boolean deleted = user1.deletePost(nonExistentPost);
                if (!deleted) {
                    throw new IllegalArgumentException("Post not found.");
                }
            } catch (Exception e) {
                System.err.println("Exception: " + e.getMessage());
            }
        }
        
    
    
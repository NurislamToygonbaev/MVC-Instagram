package instagram.controller;

import instagram.entity.Comment;
import instagram.entity.Image;
import instagram.entity.Post;
import instagram.entity.User;
import instagram.exception.MyException;
import instagram.service.CommentService;
import instagram.service.PostService;
import instagram.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final UserService userService;
    private final CommentService commentService;
    @GetMapping("/newPost")
    public String createPost(Model model){
        Post post = new Post();
        Image image = new Image();
        model.addAttribute("post", post);
        model.addAttribute("image", image);
        return "new-post";
    }
    @PostMapping("/savePost")
    public String savePost(@ModelAttribute("post") Post post,
                           @ModelAttribute("image") Image image,
                           Model model) {
        try {
            post.setImage(image);
            image.setPost(post);
            postService.createPost(post);
        } catch (MyException e) {
            model.addAttribute("errorMessage", "New Post cannot be without Image");
            return "error-page";
        }
        return "redirect:/home/profUser";
    }

    @GetMapping("/viewComment/{postId}")
    public String viewComment(Model model, @PathVariable Long postId){
        Post findPost = postService.findById(postId);
        model.addAttribute("findPost", findPost);
        model.addAttribute("newComment", new Comment());
        return "comment-page";
    }

    @PostMapping("/savedComment/{postId}")
    public String savedComment(@PathVariable Long postId,
                               @ModelAttribute("newComment") Comment comment,
                               Model model){
        model.addAttribute("postId", postId);
        commentService.saveComment(postId, comment);
        return "redirect:/posts/viewComment/" + postId;
    }

    @GetMapping("/editPost/{postId}")
    public String editPost(@PathVariable Long postId, Model model){
        Post findPOst = postService.findById(postId);
        model.addAttribute("postGetId", postId);
        model.addAttribute("editPost", findPOst);
        return "editPost-page";
    }

    @PostMapping("/savePOstAfter/{postId}")
    public String savePostAfterEdit(@PathVariable Long postId,
                                    @ModelAttribute("editPost") Post post){
        postService.updatePOst(postId, post);
        return "redirect:/home/profUser";
    }

    @GetMapping("/deletePost/{postId}")
    public String deletePost(@PathVariable Long postId){
        try {
            User user = userService.findUser();
            List<Post> posts = user.getPosts();
            for (Post post : posts) {
                if (post.getId().equals(postId)){
                    postService.deletePostById(postId);
                    posts.remove(post);
                    break;
                }
            }
        } catch (MyException e) {
            return "error-page";
        }
        return "redirect:/home/profUser";
    }
}

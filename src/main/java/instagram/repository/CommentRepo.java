package instagram.repository;

import instagram.entity.Comment;

public interface CommentRepo {
    void saveComment(Long postId, Comment comment);

    void deleteComment(Long comId);
}

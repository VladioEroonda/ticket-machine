package ru.eroonda.ticketmachine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentDto {
    @NotBlank
    @Size(min = 5, max = 1500, message = "size from 5 to 1500")
    private String commentText;

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

}

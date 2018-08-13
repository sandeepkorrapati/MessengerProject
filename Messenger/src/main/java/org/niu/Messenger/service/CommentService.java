package org.niu.Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.niu.Messenger.database.DatabaseClass;
import org.niu.Messenger.exception.DataNotFoundException;
import org.niu.Messenger.model.Comment;
import org.niu.Messenger.model.Message;

public class CommentService {
	
	private Map<Long, Message> messages = DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		ArrayList<Comment> comment = new ArrayList<Comment>(comments.values());
		if(comment.isEmpty()) {
			throw new DataNotFoundException("No Comments Found for the message Id "+ messageId );
		}
		return comment;
	}
	
	public Comment getComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comments.isEmpty()) {
			throw new DataNotFoundException("No Comment found for the message Id "+ messageId);
		}
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		comment.setId(comments.size() + 1);
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	
	public Comment updateComment(long messageId, Comment comment) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		if(comment.getId() <= 0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	
	public Comment removeComment(long messageId, long commentId) {
		Map<Long, Comment> comments = messages.get(messageId).getComments();
		return comments.remove(commentId);
	}
}

package com.tweetapp.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tweet")
public class Tweet {

	@Id
	@NotBlank
	@Indexed(unique = true)
	private int tweetId;
	@Size(max=144, message="Tweet should not go beyond 144 characters")
	private String tweetMessage;
	private String loginId;
	private Date tweetTime = new Date();
	private List<String> reply;
	private long likeCount;
	private Set<String> likedUsers = new HashSet<String>();

	public int getTweetId() {
		return tweetId;
	}

	public String getTweetMessage() {
		return tweetMessage;
	}

	public void setTweetMessage(String tweetMessage) {
		this.tweetMessage = tweetMessage;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public Tweet() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Set<String> getLikedUsers() {
		return likedUsers;
	}

	public void setLikedUsers(Set<String> likedUsers) {
		this.likedUsers = likedUsers;
	}

	public Tweet(@NotBlank int tweetId,
			@Size(max = 144, message = "Tweet should not go beyond 144 characters") String tweetMessage, String loginId,
			Date tweetTime, List<String> reply, long likeCount) {
		super();
		this.tweetId = tweetId;
		this.tweetMessage = tweetMessage;
		this.loginId = loginId;
		this.tweetTime = tweetTime;
		this.reply = reply;
		this.likeCount = likeCount;
	}

	public Date getTweetTime() {
		return tweetTime;
	}

	public void setTweetTime(Date tweetTime) {
		this.tweetTime = tweetTime;
	}

	public void setTweetId(int tweetId) {
		this.tweetId = tweetId;
	}

	public List<String> getReply() {
		return reply;
	}

	public void setReply(List<String> reply) {
		this.reply = reply;
	}

	public long getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(long likeCount) {
		this.likeCount = likeCount;
	}

}

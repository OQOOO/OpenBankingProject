package kr.ac.kopo.vo;

public class BoardVO {
	private int postId;
	private String userId;
	private String title;
	private String content;
	private String createTime;
	private int isNotice;
	private int views;
	private int commentId;
	private int reCommentId;
	private int parentReId;
	private int parentDepth;
	private int depth;
	
	
	@Override
	public String toString() {
		return "BoardVO [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", createTime=" + createTime + ", isNotice=" + isNotice + ", views=" + views + "]";
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public int getIsNotice() {
		return isNotice;
	}
	public void setIsNotice(int isNotice) {
		this.isNotice = isNotice;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getReCommentId() {
		return reCommentId;
	}
	public void setReCommentId(int reCommentId) {
		this.reCommentId = reCommentId;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getParentReId() {
		return parentReId;
	}
	public void setParentReId(int parentReId) {
		this.parentReId = parentReId;
	}
	public int getParentDepth() {
		return parentDepth;
	}
	public void setParentDepth(int parentDepth) {
		this.parentDepth = parentDepth;
	}
	
	
}

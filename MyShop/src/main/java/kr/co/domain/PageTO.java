package kr.co.domain;

import java.io.Serializable;
import java.util.List;

public class PageTO<T> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int curPage = 1;
	private int perPage = 10;
	private int perLine = 10;
	private int amount;
	private int totalPage;
	private int startNum;
	private int endNum;
	private int beginPageNum;
	private int stopPageNum;
	private List<T> list;
	
	public void executAll() {
		totalPage = (amount - 1) / perPage + 1;
		startNum = (curPage - 1) * perPage + 1;
		endNum = curPage * perPage;
		if (endNum > amount)
			endNum = amount;
		beginPageNum = (curPage - 1) / perLine * perLine + 1;
		stopPageNum = ((curPage - 1) / perLine * perLine) + perLine;
		if (stopPageNum > totalPage)
			stopPageNum = totalPage;
	}
	public PageTO() {
		executAll();
	}
	public PageTO(int curPage) {
		this.curPage = curPage;
		executAll();
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
		executAll();
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
		executAll();
	}
	public int getPerLine() {
		return perLine;
	}
	public void setPerLine(int perLine) {
		this.perLine = perLine;
		executAll();
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
		executAll();
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartNum() {
		return startNum;
	}
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	public int getEndNum() {
		return endNum;
	}
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	public int getBeginPageNum() {
		return beginPageNum;
	}
	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}
	public int getStopPageNum() {
		return stopPageNum;
	}
	public void setStopPageNum(int stopPageNum) {
		this.stopPageNum = stopPageNum;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "PageTO [curPage=" + curPage + ", perPage=" + perPage + ", perLine=" + perLine + ", amount=" + amount
				+ ", totalPage=" + totalPage + ", startNum=" + startNum + ", endNum=" + endNum + ", beginPageNum="
				+ beginPageNum + ", stopPageNum=" + stopPageNum + "]";
	}
	
	
	
	
}

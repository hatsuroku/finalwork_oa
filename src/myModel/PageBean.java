package myModel;

public class PageBean {

	private int page; // �ڼ�ҳ
	private int pageSize; // ÿҳ��¼��
	private int start; // ��ʼҳ

	public PageBean(int page, int pageSize) {
		super();
		this.page = page;
		this.pageSize = pageSize;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getStart() {
		this.start =(this.getPage()- 1 ) * pageSize;
		return start;
	}
}

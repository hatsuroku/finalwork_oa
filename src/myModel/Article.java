package myModel;
import java.sql.Date;

/**
 * ����ģ����
 * 
 * @author WYP
 *
 */
public class Article {
	// ��������
	private int id;								//id int
	private String title;						//title varchar(45)
	private String content;						//content text()
	private String department;					//department varchar(45)
	private Date date;							//date date
	// �������²�������
	public Article() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Article(int id, String title, String content,String department,Date date) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.department = department;
		this.date = date;
	}

	@Override
	public String toString() {
		return "����ID��" + getId() + "\n" + "������⣺" + getTitle() + "\n" + "�������ݣ�" + getContent() + "\n" ;
	}
	
	
}

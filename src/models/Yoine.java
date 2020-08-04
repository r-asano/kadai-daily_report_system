package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Table(name = "yoine")
@NamedQueries({
    @NamedQuery(
            name = "getSelectYoine",
            query = "SELECT y FROM Yoine AS y WHERE y.report_id = :report_id"
            )
})

@Entity
public class Yoine{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "report_id", nullable = false)
    private int report_id;

    @Column(name = "yoineCount", nullable = false)
    private int yoineCount = 0;

    public int getReportID() {
		return report_id;
	}

	public void setReportID(int report_id) {
		this.report_id = report_id;
	}

	public void setYoineCount(int yoineCount) {
        this.yoineCount = yoineCount;
    }

    public int getYoineCount() {
        return yoineCount;
    }

    public void yoinePlus() {
        int count = this.getYoineCount();
        count++;
        this.setYoineCount(count);
    }
}

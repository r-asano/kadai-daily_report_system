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
    private Report report_id;

    @Column(name = "yoineCount", nullable = false)
    private Integer yoineCount = 0;

    public Report getReportID() {
		return report_id;
	}

	public void setReportID(Report report_id) {
		this.report_id = report_id;
	}

	public void setYoineCount(Integer yoineCount) {
        this.yoineCount = yoineCount;
    }

    public Integer getYoineCount() {
        return yoineCount;
    }

    public void yoinePlus() {
        this.yoineCount++;
    }
}

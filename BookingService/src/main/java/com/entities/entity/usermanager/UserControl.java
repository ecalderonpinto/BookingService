package com.entities.entity.usermanager;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.entities.utilities.hibernate.VersionAuditor;
import com.entities.utilities.hibernate.VersionableAdapter;

@Entity
@Table(name = "T_USER_CONTROL")
public class UserControl implements VersionableAdapter, Comparable<UserControl> {

	private static final long serialVersionUID = 8589981930993526456L;

	private long id;
	private User user;
	private String message;
	private String type;
	private boolean alert;

	@Embedded
	private VersionAuditor versionAuditor;
	@Version
	@Column(name = "VERSION", nullable = false)
	int version;

	public UserControl() {
	}

	public UserControl(long id, User user, String message, boolean alert,
			VersionAuditor versionAuditor) {
		this.id = id;
		this.user = user;
		this.message = message;
		this.alert = alert;
		this.versionAuditor = versionAuditor;
	}

	public UserControl(User user, String message, boolean alert, String type,
			VersionAuditor versionAuditor) {
		this.user = user;
		this.message = message;
		this.alert = alert;
		this.type = type;
		this.versionAuditor = versionAuditor;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	// @SequenceGenerator(name = "GEN_USER_CONTROL", sequenceName =
	// "SEQ_USER_CONTROL", initialValue = 1, allocationSize = 1)
	// @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	// "GEN_USER_CONTROL")
	@Column(name = "USER_CONTROL_ID", unique = true, nullable = false, length = 40)
	public long getId() {
		return this.id;
	}

	public void setId(long userId) {
		this.id = userId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "USER_ID", foreignKey = @ForeignKey(name = "T_USER_CONTROL_FK_USER_ID"))
	@JoinColumn(name = "USER_ID")
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "CONTROL_MESSAGE", nullable = false, length = 1024)
	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Column(name = "CONTROL_ALERT", nullable = false)
	public boolean isAlert() {
		return this.alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	@Column(name = "CONTROL_TYPE", nullable = false, length = 64)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	public VersionAuditor getVersionAuditor() {
		return versionAuditor;
	}

	public void setVersionAuditor(VersionAuditor _auditor) {
		this.versionAuditor = _auditor;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof UserControl) {
			return (((UserControl) object).getUser().equals(this.user)
					&& ((UserControl) object).getMessage().equals(this.message) && ((UserControl) object)
						.isAlert() == (this.alert));
		}
		return false;
	}

	public int compareTo(UserControl o) {
		// compare last date first
		return o.getVersionAuditor().getCreationDate()
				.compareTo(getVersionAuditor().getCreationDate());
	}
}

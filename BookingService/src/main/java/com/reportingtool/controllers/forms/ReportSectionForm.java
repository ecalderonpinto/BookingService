package com.reportingtool.controllers.forms;

public class ReportSectionForm {

	private String sectionName;
	private boolean hasBlock;

	public ReportSectionForm(String sectionName, boolean hasBlock) {
		this.sectionName = sectionName;
		this.hasBlock = hasBlock;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public boolean isHasBlock() {
		return hasBlock;
	}

	public void setHasBlock(boolean hasBlock) {
		this.hasBlock = hasBlock;
	}

	@Override
	public boolean equals(Object object) {
		if (object instanceof ReportSectionForm) {
			return ((ReportSectionForm) object).getSectionName().equals(
					this.getSectionName())
					&& ((ReportSectionForm) object).isHasBlock() == this
							.isHasBlock();
		}
		return false;
	}

}

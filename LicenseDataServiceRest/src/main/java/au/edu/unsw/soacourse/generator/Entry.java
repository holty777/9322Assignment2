package au.edu.unsw.soacourse.generator;

/**
 * Entry Object used for parsing html to XML
 * @author Jack Holt z3418559
 *
 */
public class Entry {

	public String quarter;
	public String postcode;
	public String total;
	public String classC;
	public String classLR;
	public String classMR;
	public String classHR;
	public String classHC;
	public String classMC;
	public String classR;
	public String learner;
	public String p1;
	public String p2;
	public String unrestricted;

	public Entry() {

	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getClassC() {
		return classC;
	}

	public void setClassC(String classC) {
		this.classC = classC;
	}

	public String getClassLR() {
		return classLR;
	}

	public void setClassLR(String classLR) {
		this.classLR = classLR;
	}

	public String getClassMR() {
		return classMR;
	}

	public void setClassMR(String classMR) {
		this.classMR = classMR;
	}

	public String getClassHR() {
		return classHR;
	}

	public void setClassHR(String classHR) {
		this.classHR = classHR;
	}

	public String getClassHC() {
		return classHC;
	}

	public void setClassHC(String classHC) {
		this.classHC = classHC;
	}

	public String getClassMC() {
		return classMC;
	}

	public void setClassMC(String classMC) {
		this.classMC = classMC;
	}

	public String getClassR() {
		return classR;
	}

	public void setClassR(String classR) {
		this.classR = classR;
	}

	public String getLearner() {
		return learner;
	}

	public void setLearner(String learner) {
		this.learner = learner;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getUnrestricted() {
		return unrestricted;
	}

	public void setUnrestricted(String unrestricted) {
		this.unrestricted = unrestricted;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String string) {
		this.postcode = string;
	}
}
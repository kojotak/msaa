package cz.kojotak.msaa.to;

import java.util.Collections;
import java.util.List;

public class Project {

	private String name;
	private String status;
	private String source;
	private List<String> targets = Collections.emptyList();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public List<String> getTargets() {
		return targets;
	}
	public void setTargets(List<String> targets) {
		this.targets = targets;
	}
	@Override
	public String toString() {
		return "Project [name=" + name + ", status=" + status + ", source=" + source + ", targets=" + targets + "]";
	}
}

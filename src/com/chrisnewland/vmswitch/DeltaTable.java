package com.chrisnewland.vmswitch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeltaTable
{
	private List<String> added = new ArrayList<>();
	private List<String> removed = new ArrayList<>();

	private VMData earlierVM;
	private VMData laterVM;

	public DeltaTable(VMData earlierVM, VMData laterVM)
	{
		this.earlierVM = earlierVM;
		this.laterVM = laterVM;
	}

	public void recordAddition(String switchName)
	{
		added.add(switchName);
	}

	public void recordRemoval(String switchName)
	{
		removed.add(switchName);
	}

	public int getAdditionCount()
	{
		return added.size();
	}

	public int getRemovalCount()
	{
		return removed.size();
	}
	
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		Collections.sort(added);
		Collections.sort(removed);

		builder.append("<table class=\"delta\">");

		builder.append("<tr>");
		builder.append("<th>Removed in ").append(laterVM.getJdkName()).append("</th>");
		builder.append("<th>Added in ").append(laterVM.getJdkName()).append("</th>");
		builder.append("</tr>");

		builder.append("<tr>");

		builder.append("<td class=\"removed\">");
		for (String removedSwitch : removed)
		{
			builder
					.append("<a href=\"")
					.append(earlierVM.getHTMLFilename())
					.append("?s=")
					.append(removedSwitch)
					.append("\">")
					.append(removedSwitch)
					.append("</a>")
					.append("<br>");
		}
		builder.append("</td>");

		builder.append("<td class=\"added\">");
		for (String addedSwitch : added)
		{
			builder
					.append("<a href=\"")
					.append(laterVM.getHTMLFilename())
					.append("?s=")
					.append(addedSwitch)
					.append("\">")
					.append(addedSwitch)
					.append("</a>")
					.append("<br>");
		}
		builder.append("</td>");

		builder.append("</tr>");

		builder.append("</table>");

		return builder.toString();

	}
}
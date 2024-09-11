package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberExporter;
import com.smartdatasolutions.test.MemberFileConverter;
import com.smartdatasolutions.test.MemberImporter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.Writer;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

public class Main extends MemberFileConverter {

	@Override
	protected MemberExporter getMemberExporter( ) {
		// TODO
		return new MemberExporterImpl();
	}

	@Override
	protected MemberImporter getMemberImporter( ) {
		// TODO
		return new MemberImporterImpl();
	}

	@Override
	protected List< Member > getNonDuplicateMembers( List< Member > membersFromFile ) {

		// TODO
		Set<Member> set = new HashSet<>(membersFromFile);
		return new ArrayList<>(set);
	}

	@Override
	protected Map< String, List< Member >> splitMembersByState( List< Member > validMembers ) {
		// TODO
		return validMembers.stream().collect(Collectors.groupingBy(Member::getState));
	}

	public static void main(String[] args) {
	    Main converter = new Main();
	    File inputFile = new File("Members.txt");

	    try {
	    
	        List<Member> members = converter.getMemberImporter().importMembers(inputFile);
	        
	        List<Member> uniqueMembers = converter.getNonDuplicateMembers(members);

	        Map<String, List<Member>> membersByState = converter.splitMembersByState(uniqueMembers);

	        MemberExporter exporter = converter.getMemberExporter();

	        for (Map.Entry<String, List<Member>> e : membersByState.entrySet()) {
	            String state = e.getKey();
	            List<Member> membersByStateList = e.getValue();

	            String outputFileName = state + "_outputFile.csv";
	            
	            try (FileWriter writer = new FileWriter(outputFileName)) {
	                for (Member member : membersByStateList) {
	                    exporter.writeMember(member, writer);
	                }
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


}

package com.smartdatasolutions.test.impl;

import com.smartdatasolutions.test.Member;
import com.smartdatasolutions.test.MemberImporter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class MemberImporterImpl implements MemberImporter {

	@Override
	public List< Member > importMembers( File inputFile ) throws Exception {
		
		List<Member> list = new ArrayList<>();

		/*
		 * Implement the missing logic
		 */

		try (BufferedReader br = new BufferedReader( new FileReader( inputFile ) )) {
			String line = br.readLine( );

			while ( line != null ) {
				if (!line.trim().isEmpty()) {
					// Parse the line based on fixed widths
					String id = line.substring(0, 12).trim();
					String lastName = line.substring(12, 37).trim();
					String firstName = line.substring(37, 62).trim();
					String address = line.substring(62, 92).trim();
					String city = line.substring(92, 112).trim();
					String state = line.substring(112, 116).trim();
					String zip = line.substring(116, 121).trim();

					// Create a Member object
					Member member = new Member(id, firstName, lastName, address, city, state, zip);
					list.add(member);
				}
				line = br.readLine( );

			}
		}

		return list;
	}
	
	

}

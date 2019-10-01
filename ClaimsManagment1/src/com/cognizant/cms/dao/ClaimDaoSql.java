package com.cognizant.cms.dao;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import com.cognizant.cms.model.Claim;
import com.cognizant.cms.model.Member;

public class ClaimDaoSql {
	public void fileUpload(Part filePart) {

		try {
			InputStream fileContent = filePart.getInputStream();

			Connection con = ConnectionHandler.getConnection();
			int idd = 1;
			PreparedStatement stmt = con.prepareStatement("update  member set file= ? where mem_id='" + idd + "'");
			stmt.setBlob(1, fileContent);

			System.out.println("Admin Saved Succeessfully");
			stmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int verifyClaim(String memId, String clmId) {

		String memberId = memId;
		String claimId = clmId;

		Connection con = ConnectionHandler.getConnection();

		try {
			Statement s = con.createStatement();
			int claim_status = 0;
			System.out.println("dshj");
			ResultSet rs = s.executeQuery("select * from claim where claim_id='" + claimId + "' and mem_id='" + memberId
					+ "' and claim_status='open'");

			if (rs.next()) {
				return 2;

			}
			claim_status = 1;
			rs = s.executeQuery("select * from claim where claim_id='" + claimId + "' and mem_id='" + memberId
					+ "' and claim_status='close'");

			if (rs.next()) {
				return 3;

			}
			con.close();
		} catch (Exception e) {

			System.out.println(e);

		}
		return 1;

	}

	public ArrayList<Claim> getClaimListToBeProcess() {
		Connection con = ConnectionHandler.getConnection();
		ArrayList<Claim> claimList = new ArrayList<Claim>();

		try {
			PreparedStatement stmt = con.prepareStatement("select * from claim where approve_status='null'");

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {

				Claim claim = new Claim(rs.getString("claim_id"), rs.getString("mem_id"));
				claimList.add(claim);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return claimList;

	}

	public String getImageString(Blob blob) {
		String base64Image = null;
		InputStream inputStream;
		try {
			inputStream = blob.getBinaryStream();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[4096];
			int bytesRead = -1;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}

			byte[] imageBytes = outputStream.toByteArray();
			base64Image = Base64.getEncoder().encodeToString(imageBytes);
			inputStream.close();
			outputStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return base64Image;

	}

	public ArrayList<Claim> processedClaims() {
		Connection con = ConnectionHandler.getConnection();
		ArrayList<Claim> claimList = new ArrayList<Claim>();
		String query = "select * from claim where approve_status ='accept' or approve_status='reject' ";
		try {
			PreparedStatement st = con.prepareStatement(query);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Claim c = new Claim();
				c.setMemid(rs.getString("mem_id"));
				c.setClaimid(rs.getString("claim_id"));
				c.setApprove_status(rs.getString("approve_status"));
				claimList.add(c);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return claimList;
	}

	public ArrayList<Claim> trackClaims(String id) {
		Connection con = ConnectionHandler.getConnection();
		String id1 = id;
		ArrayList<Claim> claimList = new ArrayList<Claim>();
		String query = "select * from claim where mem_id = ? and claim_status = 'open' ";
		try {
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, id1);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Claim c = new Claim();
				c.setClaimid(rs.getString("claim_id"));
				c.setMemid(rs.getString("mem_id"));
				c.setApprove_status(rs.getString("approve_status"));
				claimList.add(c);
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return claimList;

	}
	public void CloseClaim(String claimid)
	{
		Connection con = ConnectionHandler.getConnection();
		String id = claimid;
		String query = "update claim set claim_status='close' where claim_id = ?";
		try 
		{
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, id);
			st.executeUpdate();
			
		} 
		catch (SQLException e)
		{
		
			e.printStackTrace();
		}
		
	}
	public ArrayList<Claim> getPreviousClaim(String memid)
	{
		Connection con = ConnectionHandler.getConnection();
		String id = memid;
		ArrayList<Claim> claimlist = new ArrayList<Claim>();
		String query = "select * from claim where mem_id = ? and claim_status = 'close' ";
		try 
		{
			PreparedStatement st = con.prepareStatement(query);
			st.setString(1, id);
			ResultSet rs = st.executeQuery();
			while(rs.next())
			{
				Claim c= new Claim();
				c.setClaimid(rs.getString("claim_id"));
				c.setApprove_status(rs.getString("approve_status"));
				claimlist.add(c);
				
			}
		} 
		catch (SQLException e)
		{
		
			e.printStackTrace();
		}
		return claimlist;
	}
}

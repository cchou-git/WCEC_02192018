package org.wcec.retreat.model;

import java.util.ArrayList;
import java.util.List;

public class RegistrationRecordLabel {
	public static String ChineseNameLabel = "中文姓名";
	public static String EnglishFirstNameLabel = "English First Name";
	public static String EnglishLastNameLabel = "English Last Name";
	public static String GenderLabel = "Gender/性别";
	public static String AdultLabel = "Adult/成人";
	public static String GreaterThanFiveYearOldLabel = ">= 5 yrs old/>= 五岁";
	public static String DonationLabel = "Donation(自由奉献）";
	public static String TotalLabel = "Total/总数";
	public static String AgeLabel = "Age/年龄";
	public static String SpecialNeedLabel = "Special Request/住宿需要";
	public static String FinancialNeedLabel = "*Need Financial Aid/需要教会经济补贴";

	public List<String> GetLabelArray() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(" ");
		list.add(ChineseNameLabel);
		list.add(EnglishFirstNameLabel);
		list.add(EnglishLastNameLabel);
		list.add(GenderLabel);
		list.add(AdultLabel);  
		list.add(GreaterThanFiveYearOldLabel); 
		list.add(DonationLabel);
		list.add(TotalLabel);
		list.add(AgeLabel);
		list.add(SpecialNeedLabel);
		list.add(FinancialNeedLabel);  
		return list; 
	}
}

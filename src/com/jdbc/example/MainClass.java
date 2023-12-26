package com.jdbc.example;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Model model = new Model();
		//model.selectOne();
		
//		System.out.print("부서 ID:");
//		int dep_id = scan.nextInt();
//		
//		System.out.print("부서 이름:");
//		String dep_name = scan.next();
//		
//		System.out.print("매니저 ID:");
//		String dep_mid = scan.next();
//		
//		System.out.print("부서 장소 ID:");
//		String dep_lid = scan.next();
//		
//		model.insertOne(dep_id, dep_name, dep_mid, dep_lid);
//		
		
//		
//		System.out.print("부서 ID:");
//		int dep_id2 = scan.nextInt();
//		
//		System.out.print("부서 이름:");
//		String dep_name2 = scan.next();
//		
//		System.out.print("매니저 ID:");
//		String dep_mid2 = scan.next();
//		
//		model.updateOne(dep_id2, dep_name2, dep_mid2);
		
//		
//		System.out.print("직원 ID:");
//		int dep_id3 = scan.nextInt();
//		
//		model.deleteOne(dep_id3);
		
		ArrayList <EmployeeVO> list = model.selectTwo();
		
		for(EmployeeVO vo : list) {
			System.out.println(vo.getEmp_id());
			System.out.println(vo.getName());
			System.out.println(vo.getDep_name());
			System.out.println(vo.getSalary());
		}
		
		
		
	}
	
}

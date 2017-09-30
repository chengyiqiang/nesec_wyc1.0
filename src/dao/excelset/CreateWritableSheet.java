package dao.excelset;

import java.util.Date;

import appdata.AppData;
import jxl.Sheet;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;


public class CreateWritableSheet {
	//sheet
	public static WritableSheet writableSheet = null;
	public static WritableFont wf = null;
	public static WritableCellFormat wcf = null;
	public static Sheet sheet = null;
	
	//��ȡsheet,������rowֵ,ͨ�������д����ִ�н��
	public static void getWritableSheet(String sheetname){
		writableSheet = CreateWritableExcel.wwb.getSheet(sheetname);
		AppData.row = 1;
	}

	//��ȡsheet,������rowֵ,ͨ�������д����ִ�н��
	public static void getSheet(String sheetname){
		sheet = CreateWritableExcel.wb.getSheet(sheetname);
	}
	
	//��ȡ��ǰsheet��ָ����Ԫ������
	public static String readWritableSheet (int column,int row){
			String content = "";
			try{
				content = writableSheet.getCell(column, row).getContents();
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("��ȡ��ǰsheet��ָ����Ԫ�������쳣");
			}
			return content;
		}
		
	//��ȡ��ǰsheet��ָ����Ԫ������
	public static String readSheet (int column,int row){
		String content = "";
		try{
			content = sheet.getCell(column, row).getContents();
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("��ȡ��ǰsheet��ָ����Ԫ�������쳣");
		}
		return content;
	}
		
	//���õ�Ԫ���ʽ
	public static WritableCellFormat setFormat (String color) {
		//ͨ��color�ж�д���������ɫ
		if (color.equals("RED")) {
		// ���õ�Ԫ���ʽΪ��13�����壬û��б�壬��ɫ����
			wf = new WritableFont(WritableFont.TIMES, 13, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.RED);
			wcf = new WritableCellFormat(wf);
		} else if (color.equals("BLUE")) {
			wf = new WritableFont(WritableFont.TIMES, 13, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLUE);
			wcf = new WritableCellFormat(wf);
		} else if (color.equals("BLACK")) {
			wf = new WritableFont(WritableFont.TIMES, 13, WritableFont.NO_BOLD,false, UnderlineStyle.NO_UNDERLINE, jxl.format.Colour.BLACK);
			wcf = new WritableCellFormat(wf);
		}
		return wcf;				
	}
		
	// ��ӵ�Ԫд������--д����Խ��
	public static void addCell(int row, boolean bool, String statue) {
		//����ͨ��true����Ϊ��ɫ��ʧ��false����Ϊ��ɫ
		if (!bool) {
			try {
				writableSheet.addCell(new Label(3, row, bool + "", setFormat("RED")));
			} catch (Exception e) {
				System.out.println("Excel����ӵ�Ԫ��ʧ�ܣ�");
			}
		} else {
			try {
				writableSheet.addCell(new Label(3, row, bool + "", setFormat("BLUE")));
			} catch (Exception e) {
				System.out.println("Excel����ӵ�Ԫ��ʧ�ܣ�");
			}
		}

		if (statue.equals("Passed")) {
			try {
				writableSheet.addCell(new Label(5, row, statue, setFormat("BLUE")));
			} catch (Exception e) {
				System.out.println("Excel����ӵ�Ԫ��ʧ�ܣ�");
			}
		} else {
			try {
				writableSheet.addCell(new Label(5, row, statue,  setFormat("RED")));
			} catch (Exception e) {
				System.out.println("Excel����ӵ�Ԫ��ʧ�ܣ�");
			}
		}

		try {
			writableSheet.addCell(new Label(4, row, AppData.sdf.format(new Date())));
		} catch (Exception e) {
			System.out.println("Excel����ӵ�Ԫ��ʧ�ܣ�");
		}
	}

}

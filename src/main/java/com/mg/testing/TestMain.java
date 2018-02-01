package com.mg.testing;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.filechooser.FileSystemView;

import org.apache.commons.codec.binary.Base64;

public class TestMain {

	public static void main(String[] args) {

		// USB1();

		// encryptDecrypt();

		// Scanner scanner = new Scanner(System.in);
		// int n = scanner.nextInt();
		// ArrayList<Integer> list = new ArrayList<>();
		// list.add(n);
		// for (int u = 0; u < n; u++) {
		// int n1 = scanner.nextInt();
		// list.add(n1);
		// }
		//
		// Integer i[] = list.toArray(new Integer[list.size()]);

		Integer i[] = { 6, 1, 2, 3, 5, 6, 6 };

		Map<Integer, Map<Integer, Integer>> m1 = new HashMap<>();

		Integer value = 0;

		for (int j = 1; j < i.length; j++) {
			Map<Integer, Integer> m2 = new HashMap<>();
			for (int k = 1; k < i.length; k++) {
				if (k == j)
					continue;
				if (k > j)
					value = i[j] + i[k] + (k - j);
				else
					value = i[j] + i[k] + (j - k);

				if (m2.get(i[k]) == null)
					m2.put(i[k], value);
				else if (m2.get(i[k]) > value)
					m2.put(i[k], m2.get(i[k]));
			}
			m1.put(i[j], m2);
		}

		Map<Integer, Integer> resultMap = new HashMap<>();

		m1.entrySet().forEach(m1Entry -> {

			Integer maxValueInMap = Collections.max(m1Entry.getValue().values());
			Integer m2EntryKey = 0;
			for (Entry<Integer, Integer> m2Entry : m1Entry.getValue().entrySet()) {
				if (m2Entry.getValue() == maxValueInMap) {
					m2EntryKey = m2Entry.getKey();
				}
			}
			Integer treeDiff = 0;
			if (m2EntryKey > m1Entry.getKey())
				treeDiff = m2EntryKey - m1Entry.getKey();
			else
				treeDiff = m1Entry.getKey() - m2EntryKey;

			resultMap.put(treeDiff, maxValueInMap);
		});

		System.out.println(Collections.max(resultMap.values()));

	}

	private static void encryptDecrypt() {
		try {
			String strip = "jnop";
			byte[] encryptArray = Base64.encodeBase64(strip.getBytes());
			String encstr = new String(encryptArray, "UTF-8");
			System.out.println("Enc   >>  " + encstr);

			String strDec = "QVRUSVVUVEFN";
			byte[] dectryptArray = strDec.getBytes();
			byte[] decarray = Base64.decodeBase64(dectryptArray);
			String decstr = new String(decarray, "UTF-8");
			System.out.println("Dec  >>>  " + decstr);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private static void USB1() {
		FileSystemView fsv = FileSystemView.getFileSystemView();
		File[] f = File.listRoots();
		List<File> fileList = new ArrayList<>();
		fileList = Arrays.asList(f);

		boolean isAuthenticDrive = fileList.stream()
				.anyMatch(file -> fsv.getSystemDisplayName(file).equalsIgnoreCase("MOHAK GUPTA (G:)")
						&& fsv.getSystemTypeDescription(file).equalsIgnoreCase("USB Drive"));

		System.out.println(isAuthenticDrive);

		for (int i = 0; i < f.length; i++) {
			System.out.println("--------------------------------------");
			System.out.println("Drive: " + f[i].getAbsolutePath().substring(0, 1));
			System.out.println("Display name: " + fsv.getSystemDisplayName(f[i]));
			System.out.println("Is drive: " + fsv.isDrive(f[i]));
			System.out.println("Is floppy: " + fsv.isFloppyDrive(f[i]));
			System.out.println("Readable: " + f[i].canRead());
			System.out.println("Writable: " + f[i].canWrite());
			System.out.println("Total space: " + f[i].getTotalSpace());
			System.out.println("Usable space: " + f[i].getUsableSpace());
			System.out.println(fsv.getSystemTypeDescription(f[i]).contains("Removable"));
		}
	}
}

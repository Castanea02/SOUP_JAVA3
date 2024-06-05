import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class book {
	class Member {
		private String name;
		private String age;
		private String gender;

		public Member(String name, String age, String gender) {
			this.name = name;
			this.age = age;
			this.gender = gender;
		}

		@Override
		public String toString() {
			return "Member{" +
					"name='" + name + "'" +
					", age='" + age + "'" +
					", gender='" + gender + "'" +
					'}';
		}

		public String getName() {
			return name;
		}
	}

	public class storeMember {
		public List<Member> members = new ArrayList<>();

		{
			// 초기화 블록을 사용한 멤버 초기화
			members.add(new Member("이영희", "22", "Female"));
			members.add(new Member("박지수", "24", "Male"));
		}

		public void displayMenu() {
			System.out.println("원하는 작업을 입력해주세요.");
			System.out.println("1. 조회");
			System.out.println("2. 추가");
			System.out.println("3. 수정");
			System.out.println("4. 삭제");
		}

		public void listMembers() {
			if (members.isEmpty()) {
				System.out.println("등록된 멤버가 없습니다.");
			} else {
				System.out.println("등록된 멤버 목록:");
				for (Member member : members) {
					System.out.println(member);
				}
			}
		}
	}

	public class delete {
		private book.storeMember storeMember;

		public delete(Main.storeMember storeMember) {
			this.storeMember = storeMember;
		}

		public void deleteMember(String name) {
			for (Member member : storeMember.members) {
				if (member.getName().equals(name)) {
					storeMember.members.remove(member);
					System.out.println(name + " 멤버가 삭제되었습니다.");
					return;
				}
			}
			System.out.println(name + " 이름의 멤버를 찾을 수 없습니다.");
		}
	}


	public class modify{

		private book.storeMember storeMember;
		private int memberNum = -1;
		public modify(Main.storeMember storemember){
			this.storeMember = storemember;
		}
		public void findModifyingMember(String name){
			int i = 0;
			for (Member member : storeMember.members) {
				if (member.getName().equals(name)) {
					memberNum =  i;
				}
				i++;
			}
		}

		public void modifyMember(String name, String age, String gender){
			Member member = new Member(name, age, gender);
			if(memberNum == -1){
				System.out.println(name + "이름의 멤버를 찾을 수 없습니다.");
			}
			else{
				storeMember.members.set(memberNum, member);
				System.out.println("멤버 수정이 완료되었습니다.");
			}
		}
	}

	public static void main(String[] args) {
		book mainInstance = new book();
		storeMember memberStore = mainInstance.new storeMember();
		delete deleteManager = mainInstance.new delete(memberStore);
		modify modifyManager = mainInstance.new modify(memberStore);
		Scanner scanner = new Scanner(System.in);

		while (true) {
			memberStore.displayMenu();
			int choice = scanner.nextInt();
			scanner.nextLine();  // Consume newline
			switch (choice) {
				case 1:
					memberStore.listMembers();
					break;
				case 2:
					// 추가 기능
					break;
				case 3:
					System.out.println("수정할 멤버의 이름을 입력해주세요:");
					String modifyName = scanner.next();
					modifyManager.findModifyingMember(modifyName);
					System.out.println("수정할 내용을 입력해주세요:");
					System.out.print("이름:");
					String name = scanner.next();
					System.out.print("나이:");
					String age = scanner.next();
					System.out.print("성별:");
					String gender = scanner.next();
					modifyManager.modifyMember(name, age, gender);
					break;
				case 4:
					System.out.println("삭제할 멤버의 이름을 입력해주세요:");
					String deleteName = scanner.nextLine();
					deleteManager.deleteMember(deleteName);
					break;
				default:
					System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
					break;
			}
		}
	}
}
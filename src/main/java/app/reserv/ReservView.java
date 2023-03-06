package app.reserv;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import app.AppService;
import app.AppView;
import dao.DAO;
import entity.TicketDTO;


public class ReservView extends AppView{
	private Reservation reserv;
	private TicketDTO ticketDTO;

	public ReservView(Reservation reserv) {
		super("예약하기", reserv);
		this.reserv = reserv;
		initRootPanel();
	}

	public void initRootPanel() {
		rootPanel = new JPanel(null);
		rootPanel.setBackground(new Color(255, 255, 255));
		rootPanel.setForeground(new Color(255, 255, 255));
		
		// 패널
		JPanel panel = new JPanel();
		panel.setBackground(new Color(224, 255, 255));
		panel.setBorder(null);
		panel.setBounds(113, 56, 466, 51);
		rootPanel.add(panel);
		
	        // 페이지 제목
		JLabel pageLbl = new JLabel("예약하기");
		pageLbl.setBounds(194, 10, 80, 27);
		pageLbl.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		panel.add(pageLbl);
		
		// 항공편
		JLabel airNumLbl = new JLabel("항공편");
		airNumLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		airNumLbl.setBounds(136, 147, 76, 24);
		rootPanel.add(airNumLbl);
		// 항공편 콤보박스
		JComboBox<String> airNumBox = new JComboBox<String>();
		List<List<String>> numList = DAO.sql.select("select distinct airnum from seat where depdate > sysdate");
		numList.forEach(l->airNumBox.addItem(l.get(0)));
		airNumBox.setBounds(307, 151, 138, 23);
		rootPanel.add(airNumBox);
		
		// 좌석 등급
		JLabel seatGrLbl = new JLabel("좌석 등급");
		seatGrLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		seatGrLbl.setBounds(136, 193, 76, 24);
		rootPanel.add(seatGrLbl);
		// 좌석 등급 콤보박스
		JComboBox seatCombo = new JComboBox();
		seatCombo.setBackground(new Color(255, 255, 255));
		seatCombo.setModel(new DefaultComboBoxModel(new String[] {"이코노미", "비즈니스", "퍼스트클래스"}));
		seatCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		seatCombo.setBounds(307, 194, 138, 23);
		rootPanel.add(seatCombo);
		
		// 출발지
		JLabel depLbl = new JLabel("출발지");
		depLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		depLbl.setBounds(136, 239, 76, 24);
		rootPanel.add(depLbl);
		// 출발지 콤보박스
		JComboBox depCombo = new JComboBox();
		depCombo.setModel(new DefaultComboBoxModel(new String[] {"인천", "부산", "제주", "김포", "런던", "바르셀로나", "다낭", "방콕", "로스앤젤레스", "싱가포르", "파리", "오사카", "도쿄/나리타"}));
		depCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		String depPlace = depCombo.getSelectedItem().toString();
		depCombo.setBounds(307, 240, 138, 23);
		rootPanel.add(depCombo);
		
		// 도착지
		JLabel arrLbl = new JLabel("도착지");
		arrLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		arrLbl.setBounds(136, 288, 76, 24);
		rootPanel.add(arrLbl);
		// 도착지 콤보박스
		JComboBox arrCombo = new JComboBox();
		arrCombo.setModel(new DefaultComboBoxModel(new String[] {"부산", "인천", "제주", "김포", "런던", "바르셀로나", "다낭", "방콕", "로스앤젤레스", "싱가포르", "파리", "오사카", "도쿄/나리타"}));
		arrCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		arrCombo.setBounds(307, 289, 138, 23);
		rootPanel.add(arrCombo);
		
		// 출발 날짜
		JLabel depDateLbl = new JLabel("출발 날짜");
		depDateLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		depDateLbl.setBounds(136, 336, 76, 24);
		rootPanel.add(depDateLbl);
		// DateChooser로 출발 날짜 선택 기본 값을 오늘로 설정
		JDateChooser depDateChooser = new JDateChooser(new Date());
		depDateChooser.setMinSelectableDate(new Date()); //오늘 이전 날은 선택 금지함
		depDateChooser.setBounds(307, 339, 138, 24);
		rootPanel.add(depDateChooser);
		
		// 도착 날짜
		JLabel arrDateLbl = new JLabel("도착 날짜");
		arrDateLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		arrDateLbl.setBounds(136, 390, 76, 24);
		rootPanel.add(arrDateLbl);
		// DateChooser로 도착 날짜 선택 기본값을 2일후로 설정 
		JDateChooser arrDateChooser = new JDateChooser
				(new Date(System.currentTimeMillis()+(86400 * 2 * 1000) ));
		arrDateChooser.setMinSelectableDate(new Date()); //오늘 이전 날은 선택 금지함
		arrDateChooser.setBounds(307, 393, 138, 24);
		rootPanel.add(arrDateChooser);
		
		// 인원 수
		JLabel humanCntLbl = new JLabel("인원 수");
		humanCntLbl.setFont(new Font("맑은 고딕", Font.BOLD, 17));
		humanCntLbl.setBounds(136, 438, 76, 24);
		rootPanel.add(humanCntLbl);
		// 성인
		JLabel adultLbl = new JLabel("성인");
		adultLbl.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 17));
		adultLbl.setBounds(279, 440, 57, 21);
		rootPanel.add(adultLbl);
		//성인 수 선택
		JComboBox adultCombo = new JComboBox();
		adultCombo.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5"}));
		adultCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		adultCombo.setBounds(330, 439, 54, 23);
		rootPanel.add(adultCombo);
		// 소아
		JLabel kidLbl = new JLabel("소아");
		kidLbl.setFont(new Font("맑은 고딕 Semilight", Font.BOLD, 17));
		kidLbl.setBounds(405, 440, 57, 21);
		rootPanel.add(kidLbl);
		// 소아 수 선택
		JComboBox kidCombo = new JComboBox();
		kidCombo.setModel(new DefaultComboBoxModel(new String[] {"0", "1", "2", "3", "4", "5"}));
		kidCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		kidCombo.setBounds(457, 439, 54, 23);
		rootPanel.add(kidCombo);
		
		// 뒤로 가기: 예약하기 전 페이지로 돌아감
		JButton backBtn = new JButton("뒤로 가기");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reserv.openAirplaneView();
			}
		});
		backBtn.setForeground(new Color(255, 0, 0));
		backBtn.setBackground(new Color(224, 255, 255));
		backBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		backBtn.setBounds(113, 500, 132, 40);
		rootPanel.add(backBtn);
		
		//초기화: 모든 선택 값을 초기 상태로 되돌림
		JButton resetBtn = new JButton("초기화");
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				seatCombo.setSelectedIndex(0);
				depCombo.setSelectedIndex(0);
				depDateChooser.setCalendar(null);
				arrDateChooser.setCalendar(null);
				arrCombo.setSelectedIndex(0);
				adultCombo.setSelectedIndex(0);
				kidCombo.setSelectedIndex(0);
			}
		});
		resetBtn.setForeground(new Color(0, 0, 0));
		resetBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		resetBtn.setBackground(new Color(224, 255, 255));
		resetBtn.setBounds(279, 500, 132, 40);
		rootPanel.add(resetBtn);
		
		//--------------------------------------------------------------------------
		
		// 다음 단계: 누르면 선택한 값들이 DTO로 전달되고 좌석 선택 페이지로 이동
		JButton nextBtn = new JButton("다음 단계");
		
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 좌석 등급 선택 값 가져오기
				String seatGrade = seatCombo.getSelectedItem().toString();
				System.out.println(seatGrade);
				
				// 출발지 선택 값 가져오기
				String depPlace = depCombo.getSelectedItem().toString();
				System.out.println(depPlace);
				
				// 도착지 선택 값 가져오기
				String arrPlace = arrCombo.getSelectedItem().toString();
				System.out.println(arrPlace);
				
				// 출발 날짜 선택 값 가져오기
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String depDate = sdf.format(depDateChooser.getDate());
				// 출발일이 비어있는 경우
				if(depDate == null) {
					JOptionPane.showMessageDialog(null, "출발일을 선택하지 않으셨습니다.");
				}
				System.out.println(depDate);
				
				// 도착 날짜 선택 값 가져오기
				String arrDate = sdf.format(arrDateChooser.getDate());
				// 도착일이 비어있는 경우
				if(arrDate == null) {
					JOptionPane.showMessageDialog(null, "도착일을 선택하지 않으셨습니다.");
				}
				System.out.println(arrDate);
				
				// 인원 수 값 가져오기(int로 변환 후)
				int adultCnt = Integer.valueOf(adultCombo.getSelectedItem().toString());
				int kidCnt = Integer.valueOf(kidCombo.getSelectedItem().toString());
				int humanCnt = adultCnt + kidCnt;
				System.out.println("인원 수: " + humanCnt + " 성인: " + adultCnt + " 소아: " + kidCnt);
				
				// DTO로 선택 값 전송
				ticketDTO = new TicketDTO();
				ticketDTO.setCustomerId(AppService.instance().getAttr("id"));
				ticketDTO.setCustomerName(AppService.instance().getAttr("name"));
				ticketDTO.setAirNum(airNumBox.getSelectedItem().toString());
				ticketDTO.setSeatGrade(seatGrade);
				ticketDTO.setDepPlace(depPlace);
				ticketDTO.setArrPlace(arrPlace);
				ticketDTO.setDepDate(depDate);
				ticketDTO.setArrDate(arrDate);
				ticketDTO.setAdultCnt(adultCnt);
				ticketDTO.setKidCnt(kidCnt);
				// DB 테이블 "TICKET"에 추가
				
				// 알림 메시지 - 메시지 출력 / 내용이 같으면 출력 후 선택 값 초기화
				if(depPlace.equals(arrPlace)) {
					JOptionPane.showMessageDialog(null, "출발지와 도착지가 같습니다. 다시 선택해주세요.", "중복 오류", JOptionPane.WARNING_MESSAGE);
					depCombo.setSelectedIndex(0);
					arrCombo.setSelectedIndex(0);
				} else if(depDate.equals(arrDate)) {
					JOptionPane.showMessageDialog(null, "출발일과 도착일이 같습니다. 다시 선택해주세요.", "중복 오류", JOptionPane.WARNING_MESSAGE);
					depDateChooser.setCalendar(null);
					arrDateChooser.setCalendar(null);
				} else if(humanCnt > 5){
					JOptionPane.showMessageDialog(null, "본인 포함한 5명을 초과하여 예약할 수 없습니다. \n단체 예약은 관리자에게 문의하시기 바랍니다.", "5명 초과", JOptionPane.INFORMATION_MESSAGE);
				} else if(e!=null) { // 해당 사항 없으면 좌석 선택 페이지로 이동
					reserv.openSeatView(ticketDTO);
					return;
				}
				
			}
			
		});
		nextBtn.setForeground(new Color(30, 144, 255));
		nextBtn.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		nextBtn.setBackground(new Color(224, 255, 255));
		nextBtn.setBounds(447, 500, 132, 40);
		rootPanel.add(nextBtn);
	}
	
	public boolean validate() {
		return false;
	}
}

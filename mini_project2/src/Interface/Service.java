package Interface;

import java.util.List;

import Dto.MemberDto;
import Dto.OpenDto;
import Dto.RegL;
import Dto.RegisterDto;
import Dto.SubjectDto;

public interface Service {
	//������
	List<SubjectDto> listSub(); //��ϵ� ���� ��� ���
	
	//����
	boolean addOpen(OpenDto od); //���� ����
	List<OpenDto> listOpen(int num); //������ ������ ���� ��� ���(Open���̺��� ����num�� ��)
	boolean modOpen(OpenDto od); //������ ������ ���� ����
	boolean delOpen(int num); //������ ������ ���� ����
	
	//�л�
	int addReg(RegisterDto rd); //���� ��û(���� 0:����, 1:����, 2:�ο��ʰ�)
	boolean delReg(int num); //���� ���(Register�� num)
	List<RegL> listReg(int num); //�л��� ������û�� ���(Register���̺��� �л��� num������ �˻�)
	
	//�α��� ����
	boolean join(MemberDto m); //ȸ������
	boolean login(int num, String name, int type); //�α���
	boolean addSub(String sd);
}

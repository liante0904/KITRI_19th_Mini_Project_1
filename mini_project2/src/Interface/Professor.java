package Interface;

import java.util.List;

import Dto.OpenDto;

public interface Professor {
	boolean addOpen(OpenDto od);
	List<OpenDto> listOpen(int num);
	//������ȣ�� ������ ���� ��� ���
	boolean modOpen(OpenDto od);
	boolean delOpen(int num);
	//���������ȣ�� ���� ���� ����(Open)
}

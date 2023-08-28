package members.service;

import common.exception.WrongEmailPasswordException;
import members.dao.MembersDAO;
import members.dto.AuthInfo;
import members.dto.ChangePwdCommand;
import members.dto.MembersDTO;

public class MembersServiceImp implements MembersService{
	private MembersDAO membersDao;
	
	public MembersServiceImp() {
		
	}
	
	public void setMembersDao(MembersDAO membersDao) {
		this.membersDao = membersDao;
	}

	@Override
	public AuthInfo addMemberProcess(MembersDTO dto) {
		// 회원가입하고 바로 로그인 처리
		membersDao.insertMember(dto);
		return new AuthInfo(dto.getMemberEmail(), dto.getMemberName(), dto.getMemberPass());
	}

	@Override
	public AuthInfo loginProcess(MembersDTO dto) {
		MembersDTO member= membersDao.selectByEmail(dto.getMemberEmail());
		if(member==null){//회원이 아니면
			// System.out.println("회원이 아닙니다.");
			throw new WrongEmailPasswordException();
		}
		if(!member.matchPassword(dto.getMemberPass()))
		{
			// System.out.println("비밀번호가 다릅니다.");
			throw new WrongEmailPasswordException();
		}
		return new AuthInfo(member.getMemberEmail(), member.getMemberName(), member.getMemberPass());
	}

	@Override 					
	public MembersDTO updateMemberProcess(String memberEmail) {
		return membersDao.selectByEmail(memberEmail);
	}


	@Override
	public AuthInfo updateMemberProcess(MembersDTO dto) {
		membersDao.updateMember(dto);
		MembersDTO member= membersDao.selectByEmail(dto.getMemberEmail());
		return new AuthInfo(member.getMemberEmail(), member.getMemberName(), member.getMemberPass());
	}

	@Override
	public void updatePassProcess(String memberEmail, ChangePwdCommand changePwd) {
		MembersDTO member = membersDao.selectByEmail(memberEmail);
		if(member == null)
			throw new WrongEmailPasswordException();
		
		member.changepassword(changePwd.getCurrentPassword(), changePwd.getNewPassword());
		membersDao.updateByPass(member);
		
	}

}















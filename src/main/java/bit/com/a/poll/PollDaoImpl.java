package bit.com.a.poll;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PollDaoImpl implements PollDao {

	@Autowired
	SqlSession session;
	
	String ns = "Poll.";

	@Override
	public List<PollDto> getPollAllList() {		
		return session.selectList(ns + "getPollAllList");
	}

	@Override
	public int isVote(Voter voter) {		
		return session.selectOne(ns + "isVote", voter);
	}

	@Override
	public void makePoll(PollDto poll) {
		session.insert(ns + "makePoll", poll);
	}

	@Override
	public void makePollSub(PollSubDto pollsub) {
		session.insert(ns + "makePollSub", pollsub);
	}

	@Override
	public PollDto getPoll(PollDto poll) {		
		return session.selectOne(ns + "getPoll", poll);
	}

	@Override
	public List<PollSubDto> getPollSubList(PollDto poll) {		
		return session.selectList(ns + "getPollSubList", poll);		
	}

	@Override
	public void pollingVoter(Voter voter) {
		session.insert(ns + "pollingVoter", voter);
	}

	@Override
	public void pollingPoll(Voter voter) {
		session.update(ns + "pollingPoll", voter);
	}

	@Override
	public void pollingSub(Voter voter) {
		session.update(ns + "pollingSub", voter);
	}
	
	
	
	
}







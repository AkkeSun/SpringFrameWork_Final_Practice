package bit.com.a.poll;

import java.util.List;

public interface PollDao {

	List<PollDto> getPollAllList();	
	int isVote(Voter voter);
	
	void makePoll(PollDto poll);
	void makePollSub(PollSubDto pollsub);
	
	PollDto getPoll(PollDto poll);
	List<PollSubDto> getPollSubList(PollDto poll);
	
	void pollingVoter(Voter voter);
	void pollingPoll(Voter voter);
	void pollingSub(Voter voter);
}











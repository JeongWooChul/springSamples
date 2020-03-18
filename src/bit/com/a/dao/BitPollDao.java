package bit.com.a.dao;

import java.util.List;

import bit.com.a.model.PollDto;
import bit.com.a.model.PollSubDto;
import bit.com.a.model.Voter;

public interface BitPollDao {
	
	public List<PollDto> getPollAllList();
	public int isVote(Voter voter);

	public void makePoll(PollDto poll);
	public void makePollSub(PollSubDto pollsub);
	
	
}

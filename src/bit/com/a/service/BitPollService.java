package bit.com.a.service;

import java.util.List;

import bit.com.a.model.PollBean;
import bit.com.a.model.PollDto;

public interface BitPollService {

	public List<PollDto> getPollAllList(String id);
	
	public void makePoll(PollBean pbean);
}

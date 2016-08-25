package com.mygdx.game.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.mygdx.game.game.GameBoardLogic;
import com.mygdx.game.game.Rule;

public class LogicTests {
	
	Rule rule;
	
	@Before
	public void initialize(){
		rule = new Rule(new GameBoardLogic());
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}

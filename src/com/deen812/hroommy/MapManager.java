package com.deen812.hroommy;

import objects.Boxes.BoxType;

public class MapManager {

public	MapManager() {
	
}
	
public void initMap() {  }

	
private static final LevelDef[] AvailableLevels = new LevelDef[] {
	//--- 1 --------------------------------------------------------------------------------
	/*
	 * œŒÀ»√ŒÕ
	 */
	//new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.Zombie,60)}, "bg1"),
	
	//new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.Zombie2,60)}, "bg1"),
	
	//new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.Dog,60)}, "bg1"),
	
//new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.ZomieHead,60)}, "bg1"),
	
	//new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.ZBoss1,60)}, "bg1"),
	
//	new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.Boss2,60)}, "bg1"),

//	new LevelDef(1,new EnemiesInLevelDef[] {new EnemiesInLevelDef(100f, 0f, EnemiesInLevelDef.EnemyType.Boss2,60)}, "bg1"),
	
	//--- 1 -----------------------------------------------------------------------------------
		new LevelDef(new EnemiesInLevelDef[] {	
				    new EnemiesInLevelDef(900f, 50f, BoxType.Finish),
		        	new EnemiesInLevelDef(500f, 600f, BoxType.Rope),
		        	new EnemiesInLevelDef(200f, 400f, BoxType.Kaktus1),
		        	new EnemiesInLevelDef(200f, 300f,  BoxType.Box1)},
		        	1000,
		        	1000
		        ),
				        
	
};



// ====================================================
// CLASSES
// ====================================================
public static class EnemiesInLevelDef {

	public final float mX;
	public final float mY;
	public final BoxType mEnemyType;
	public float time = 1;
	
	public EnemiesInLevelDef(final float pX, final float pY, final BoxType pEnemyType) {
		mX = pX;
		mY = pY;
		mEnemyType = pEnemyType;
	}
}
public static class LevelDef {
public final EnemiesInLevelDef[] Enemies;
public int width;
public int height;

	
	public LevelDef(final EnemiesInLevelDef[] pEnemies
			,final int width
			,final int height
			) 
	{
		this.Enemies = pEnemies;
		this.width = width;
		this.height = height;


	}
	

}
public LevelDef GetLevel(int i) {
	
	return this.AvailableLevels[i];
	
}






}



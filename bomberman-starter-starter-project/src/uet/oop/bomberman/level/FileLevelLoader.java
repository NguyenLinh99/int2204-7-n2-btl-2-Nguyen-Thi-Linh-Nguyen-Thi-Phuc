package uet.oop.bomberman.level;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.LayeredEntity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.*;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.destroyable.Brick;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.exceptions.LoadLevelException;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

	/**
	 * Ma tráº­n chá»©a thÃ´ng tin báº£n Ä‘á»“, má»—i pháº§n tá»­ lÆ°u giÃ¡ trá»‹ kÃ­ tá»± Ä‘á»�c Ä‘Æ°á»£c
	 * tá»« ma tráº­n báº£n Ä‘á»“ trong tá»‡p cáº¥u hÃ¬nh
	 */
	private static char[][] _map;

	public FileLevelLoader(Board board, int level) throws LoadLevelException {
		super(board, level);
	}

	@Override
	public void loadLevel(int level) {
		// TODO: Ä‘á»�c dá»¯ liá»‡u tá»« tá»‡p cáº¥u hÃ¬nh /levels/Level{level}.txt
		// TODO: cáº­p nháº­t cÃ¡c giÃ¡ trá»‹ Ä‘á»�c Ä‘Æ°á»£c vÃ o _width, _height, _level, _map
		ClassLoader c = ClassLoader.getSystemClassLoader();
		File file = new File(c.getResource("levels/Level" + level +".txt").getFile());

		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferReader = new BufferedReader(fileReader);

			String[] line1 =  bufferReader.readLine().split(" ");
			_level = Integer.parseInt(line1[0]);
			_height = Integer.parseInt(line1[1]);
			_width = Integer.parseInt(line1[2]);

			_map = new char[_height][_width];
			for(int i = 0; i < _height; i++) {
				String line = bufferReader.readLine();
				for(int j = 0; j < _width; j++) {
					_map[i][j] = line.charAt(j);
				}
			}
			bufferReader.close();
			fileReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}



	}

	@Override
	public void createEntities() {
		// TODO: táº¡o cÃ¡c Entity cá»§a mÃ n chÆ¡i
		// TODO: sau khi táº¡o xong, gá»�i _board.addEntity() Ä‘á»ƒ thÃªm Entity vÃ o game

		// TODO: pháº§n code máº«u á»Ÿ dÆ°á»›i Ä‘á»ƒ hÆ°á»›ng dáº«n cÃ¡ch thÃªm cÃ¡c loáº¡i Entity vÃ o game
		// TODO: hÃ£y xÃ³a nÃ³ khi hoÃ n thÃ nh chá»©c nÄƒng load mÃ n chÆ¡i tá»« tá»‡p cáº¥u hÃ¬nh
		// thÃªm Wall
//		for (int x = 0; x < 20; x++) {
//			for (int y = 0; y < 20; y++) {
//				int pos = x + y * _width;
//				Sprite sprite = y == 0 || x == 0 || x == 10 || y == 10 ? Sprite.wall : Sprite.grass;
//				_board.addEntity(pos, new Grass(x, y, sprite));
//			}
//		}
//
//		// thÃªm Bomber
//		int xBomber = 1, yBomber = 1;
//		_board.addCharacter( new Bomber(Coordinates.tileToPixel(xBomber), Coordinates.tileToPixel(yBomber) + Game.TILES_SIZE, _board) );
//		Screen.setOffset(0, 0);
//		_board.addEntity(xBomber + yBomber * _width, new Grass(xBomber, yBomber, Sprite.grass));
//
//		// thÃªm Enemy
//		int xE = 2, yE = 1;
//		_board.addCharacter( new Balloon(Coordinates.tileToPixel(xE), Coordinates.tileToPixel(yE) + Game.TILES_SIZE, _board));
//		_board.addEntity(xE + yE * _width, new Grass(xE, yE, Sprite.grass));
//
//		// thÃªm Brick
//		int xB = 3, yB = 1;
//		_board.addEntity(xB + yB * _width,
//				new LayeredEntity(xB, yB,
//					new Grass(xB, yB, Sprite.grass),
//					new Brick(xB, yB, Sprite.brick)
//				)
//		);
//
//		// thÃªm Item kÃ¨m Brick che phá»§ á»Ÿ trÃªn
//		int xI = 1, yI = 2;
//		_board.addEntity(xI + yI * _width,
//				new LayeredEntity(xI, yI,
//					new Grass(xI ,yI, Sprite.grass),
//					new SpeedItem(xI, yI, Sprite.powerup_flames),
//					new Brick(xI, yI, Sprite.brick)
//				)
//		);

		for(int i = 0;i< _height  ; i++ ) {
			for(int j=0;j< _width; j++) {
				int pos = j + i * _width;

				switch(_map[i][j]) { //
					case '#':
						_board.addEntity(pos, new Wall(j, i, Sprite.wall));
						break;
					case 'b':
						_board.addEntity(pos, new LayeredEntity(j, i,
								new Grass(j ,i, Sprite.grass),
								new BombItem(j, i, Sprite.powerup_bombs),
								new Brick(j ,i, Sprite.brick)));
						break;
					case 's':
						_board.addEntity(pos, new LayeredEntity(j, i,
								new Grass(j ,i, Sprite.grass),
								new SpeedItem(j, i, Sprite.powerup_speed),
								new Brick(j ,i, Sprite.brick)));
						break;
					case 'f':
						_board.addEntity(pos , new LayeredEntity(j, i,
								new Grass(j ,i, Sprite.grass),
								new FlameItem(j, i, Sprite.powerup_flames),
								new Brick(j ,i, Sprite.brick)) );
						break;
					case '*':
						_board.addEntity(pos, new LayeredEntity(j, i,
								new Grass(j ,i, Sprite.grass),
								new Brick(j ,i, Sprite.brick)) );
						break;
					case 'x':
						_board.addEntity(pos, new LayeredEntity(j, i,
								new Grass(j ,i, Sprite.grass),
								new Portal(j ,i, Sprite.portal,_board),
								new Brick(j ,i, Sprite.brick)) );
						break;
					case ' ':
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
					case 'p':
						_board.addCharacter( new Bomber(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board) );
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;

					case '1':
						_board.addCharacter( new Balloon(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
					case '2':
						_board.addCharacter( new Oneal(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
					case '3':
						_board.addCharacter( new Doll(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
					case '4':
						_board.addCharacter( new Minvo(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
					case '5':
						_board.addCharacter( new Kondoria(Coordinates.tileToPixel(j), Coordinates.tileToPixel(i) + Game.TILES_SIZE, _board));
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
					default:
						_board.addEntity(pos, new Grass(j, i, Sprite.grass) );
						break;
				}
			}
		}

	}
}
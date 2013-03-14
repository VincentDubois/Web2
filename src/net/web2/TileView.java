/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.web2;

import android.content.Context;
import java.util.ArrayList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;

/**
 * TileView: a View-variant designed for handling arrays of "icons" or other
 * drawables.
 * 
 */
public class TileView extends View {

    /**
     * Parameters controlling the size of the tiles and their range within view.
     * Width/Height are in pixels, and Drawables will be scaled to fit to these
     * dimensions. X/Y Tile Counts are the number of tiles that will be drawn.
     */
	int w, h;

    protected static int mXTileCount;
    protected static int mYTileCount;
    
    private static final int VIDE = 0;
    private static final int ROUTE = 1;
    private static final int TOUR = 2;
    
    public int vie;
	public int argent;
    
    public int mTileWidth;
    public int mTileHeight;

	private Wave vague_monstres;

    private Matrix transform;
	private Matrix intransform;
	
	private Bitmap bmp_ennemi;
	private Chemin chemin;

	
	private ArrayList<Tour> liste_Tours;
	
    /**
     * A hash that maps integer handles specified by the subclasser to the
     * drawable that will be used for that reference
     */
    private Bitmap[] mTileArray; 

    /**
     * A two-dimensional array of integers in which the number represents the
     * index of the tile that should be drawn at that locations
     */
    private int[][] mTileGrid;

    
    private void initTileView() {
        setFocusable(true);
        Resources r = this.getContext().getResources();
        resetTiles(4);
        loadTile(VIDE, r.getDrawable(R.drawable.herbe));
        loadTile(TOUR, r.getDrawable(R.drawable.tour));
        loadTile(ROUTE, r.getDrawable(R.drawable.chemin));
        bmp_ennemi = loadImage(R.drawable.ennemi);
      
    }
    

    private final Paint mPaint = new Paint();
    
    void init(){
    	vie = 10; //Valeur temporaire pour le moment
		argent = 500; //Valeur temporaire pour le moment
        initTileView();
        mTileGrid = new int[][]{
        		{1,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
        		{0,0,2,1,0,0,0,0,0,0,0,0,0,0,0},
        		{0,1,1,1,0,0,0,0,0,0,0,0,0,0,0},
        		{0,1,0,0,0,0,1,1,1,1,0,0,0,0,0},
        		{0,1,0,0,0,0,1,0,0,1,2,0,0,0,0},
        		{0,1,2,0,0,2,1,0,0,1,1,1,1,0,0},
        		{0,1,1,1,1,1,1,0,0,0,0,2,1,0,0},
        		{0,0,0,0,0,0,0,0,0,0,0,0,1,2,0},
        		{0,0,0,0,0,0,0,0,0,0,0,0,1,1,1}
        		};
        mYTileCount = mTileGrid.length;
        mXTileCount = mTileGrid[0].length;
        chemin = new Chemin(this);
		vague_monstres = new Wave(bmp_ennemi, chemin);
		
		update();
    }

    public void ajout(int x, int y){
    	if (getTile(x, y) == VIDE && argent >= 100){
    		setTile(TOUR, x, y);
			argent -= 100;
    	}
    }
    
    public void suppression(int x, int y){
    	if (getTile(x, y) == TOUR){
    		setTile(VIDE, x, y);
			argent += 50;
    		invalidate();
    	}
    }
    
    
    
    public TileView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
          
/* 		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TileView);

        mTileSize = a.getInt(R.styleable.TileView_tileSize, 12);
        
        a.recycle();
*/
    
    public TileView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TileView(Context context) {
        super(context);
        init();
    }
    
    /**
     * Rests the internal array of Bitmaps used for drawing tiles, and
     * sets the maximum index of tiles to be inserted
     * 
     * @param tilecount
     */
    
    public void resetTiles(int tilecount) {
    	mTileArray = new Bitmap[tilecount];
    }

    /**
     * Function to set the specified Drawable as the tile for a particular
     * integer key.
     * 
     * @param key
     * @param tile
     */
    public void loadTile(int key, Drawable tile) {
    	mTileWidth = tile.getIntrinsicWidth();
    	mTileHeight = tile.getIntrinsicHeight();
        Bitmap bitmap = Bitmap.createBitmap(mTileWidth, mTileHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        tile.setBounds(0, 0, mTileWidth, mTileHeight);
        tile.draw(canvas);
        mTileArray[key] = bitmap;
    }
    
	public Bitmap loadImage(int key) {
		Resources r = this.getContext().getResources();
		Drawable drawable = r.getDrawable(key);
		int x = drawable.getIntrinsicWidth();
		int y = drawable.getIntrinsicHeight();
		Bitmap bitmap = Bitmap.createBitmap(x, y, Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.setBounds(0, 0, x, y);
		drawable.draw(canvas);
		return bitmap;
	}

    /**
     * Resets all tiles to 0 (empty)
     * 
     */
    public void clearTiles() {
        for (int x = 0; x < mXTileCount; x++) {
            for (int y = 0; y < mYTileCount; y++) {
                setTile(0, x, y);
            }
        }
    }

    /**
     * Used to indicate that a particular tile (set with loadTile and referenced
     * by an integer) should be drawn at the given x/y coordinates during the
     * next invalidate/draw cycle.
     * 
     * @param tileindex
     * @param x
     * @param y
     */
    public void setTile(int tileindex, int x, int y) {
        mTileGrid[y][x] = tileindex;
    }
    
    public int getTile(int x, int y){
    	return mTileGrid[y][x];
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.concat(transform);
		canvas.drawText(String.valueOf(vie), 0, 0, mPaint);
		canvas.drawText(String.valueOf(argent), 0, 50, mPaint);
        for (int i = 0; i < mXTileCount; i++) {
            for (int j = 0; j < mYTileCount; j++) {
                if (getTile(i, j) >= 0) {
                    canvas.drawBitmap(mTileArray[getTile(i, j)], 
                    		getX(i),
                    		getY(j),
                    		mPaint);
                }
            }
        }
		vague_monstres.draw(canvas);
    }
    
	private RefreshHandler mRedrawHandler = new RefreshHandler();

	class RefreshHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			TileView.this.update();
			TileView.this.invalidate();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};
    
    public void update() {
		if(vague_monstres.arrived(chemin))	vie--;
    	else vague_monstres.move();
		mRedrawHandler.sleep(2000);
    }
    
    public int getI(float x){
    	return (int) FloatMath.floor(x / mTileWidth);
    }
    
    public int getJ(float y){
    	return (int) FloatMath.floor(y / mTileHeight);
    }
    
    public float getX(float position){
    	return (float) (position * mTileWidth);
    }
    
    public float getY(float position){
    	return (float) (position * mTileHeight);
    }
     
    // Evenement du clic souris pour ajout des tours
    @Override
	public boolean onTouchEvent(MotionEvent event) {
		if(event.getAction() == MotionEvent.ACTION_UP){
	    	// Conversion des event.x et event.y
			float[] tabFloat = new float[]{event.getX(), event.getY()};
			intransform.mapPoints(tabFloat);
	    	int i = getI(tabFloat[0]);
	    	int j = getJ(tabFloat[1]);
			if(getTile(i, j) == VIDE)		ajout(i, j); // methode ajout d'une tour
			else if(getTile(i, j) == TOUR)	suppression(i, j); // methode suppression d'une tour
		}
		return true;
	}
    
    //Gestion taille ecran 
    @Override
    protected void onSizeChanged(int largeur, int hauteur, int ancien_largeur, int ancien_hauteur) {
    	super.onSizeChanged(largeur, hauteur, ancien_largeur, ancien_hauteur);
        transform = new Matrix();
		intransform = new Matrix();
		RectF rectVoulu = new RectF(0, 0, mTileWidth * mXTileCount, mTileHeight * mYTileCount);
		RectF rectReel = new RectF(0, 0, largeur, hauteur);
		transform.setRectToRect(rectVoulu, rectReel, Matrix.ScaleToFit.CENTER);	
		transform.invert(intransform);
	}
    
}

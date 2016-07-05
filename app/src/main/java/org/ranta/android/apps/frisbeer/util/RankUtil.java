package org.ranta.android.apps.frisbeer.util;

import org.ranta.android.apps.frisbeer.R;

/**
 * Utility for converting ranks to numbers (hopefully will be replaced by API)
 */
public class RankUtil {

    /**
     * Get the corresponding drawable identifier for a given rank
     * @param rank  Rank as string (as received from API)
     * @return      Resource id of the appropriate drawable, -1 if not found
     */
    public static int getDrawable(String rank) {
        switch (rank) {
            case "Klipsu I":
                return R.drawable.silver1;
            case "Klipsu II":
                return R.drawable.silver2;
            case "Klipsu III":
                return R.drawable.silver3;
            case "Klipsu IV":
                return R.drawable.silver4;
            case "Klipsu Mestari":
                return R.drawable.silver5;
            case "Klipsu Eliitti Mestari":
                return R.drawable.silverem;
            case "Kultapossu I":
                return R.drawable.gold1;
            case "Kultapossu II":
                return R.drawable.gold2;
            case "Kultapossu III":
                return R.drawable.gold3;
            case "Kultapossu Mestari":
                return R.drawable.gold4;
            case "Mestari Heittäjä I":
                return R.drawable.mg1;
            case "Mestari Heittäjä II":
                return R.drawable.mg2;
            case "Mestari Heittäjä Eliitti":
                return R.drawable.mge;
            case "Arvostettu Jallu Mestari":
                return R.drawable.dmg;
            case "Legendaarinen Nalle":
                return R.drawable.eagle;
            case "Legendaarinen Nalle Mestari":
                return R.drawable.eagle2;
            case "Korkein Ykkösluokan Mestari":
                return R.drawable.supreme;
            case "Urheileva Alkoholisti":
                return R.drawable.global;
            default:
                return -1;
        }
    }
}

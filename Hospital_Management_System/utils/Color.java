package utils;

/**
 *Class for colour display interface for respective user roles
 */
public class Color {
    /**
     *
     */
    public enum ColorRole {
        PATIENT("\u001B[96m"),         // Light Cyan
        DOCTOR("\u001B[94m"),          // Light Blue
        ADMINISTRATOR("\u001B[93m"),   // Light Yellow
        PHARMACIST("\u001B[92m");      // Light Green
    
        private final String colorCode;

        /**
         * @param colorCode
         */
        ColorRole(String colorCode) {
            this.colorCode = colorCode;
        }

        /**
         * @return
         */
        public String getColorCode() {
            return colorCode;
        }
    }
}

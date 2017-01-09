package com.saksham.satyam.navigationdrawerexample.macro;


import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Macro {
    private static final char BIMARY_VALUE_PREFIX = '&';
    private static final char CTRL_VALUE_PREFIX = '^';
    private static final char DECIMAL_VALUE_PREFIX = '@';
    private static final char HEXA_VALUE_PREFIX = '#';
    private static final String MACRO_DESCRIPTION_SUFFIX = ":DESC";
    public static final String MACRO_PREFERENCE_NAME = "macros";
    private static final String REG_PATTERN = "([^#@&]+)|(##|@@|&&)|(@[0-9]{3})|#\\p{XDigit}{2}|&[01]{8}";
    private static final char SPECIAL_COMMAND_PREFIX = '\\';
    private String[] macros = new String[7];
    private String[] macrosDesc = new String[7];
    private static boolean bDebugMesg = true;

    private static int fromBin2Decimal(String paramString) {
        int i = 0;
        for (int j = 1; ; j++) {
            if (j > 8) {
                return i;
            }
            if ('1' == paramString.charAt(j)) {
                i = (int) (i + Math.pow(2.0D, 8 - j));
            }
        }
    }

    private static byte fromHex2Decimal(char paramChar) {
        if (paramChar < 'A') {
            return (byte) (paramChar - '0');
        }
        return (byte) (10 + (byte) (paramChar - 'A'));
    }

    private static int fromHex2Decimal(String paramString) {
        String str = paramString.toUpperCase();
        int i = fromHex2Decimal(str.charAt(1));
        int j = fromHex2Decimal(str.charAt(2));
        DumpMsg("fromHex2Decimal: " + (j + i * 16));
        return j + i * 16;
    }

    private static void DumpMsg(Object s) {
        if (true == bDebugMesg) {
            Log.d("PL2303MultiUSBApp", ">==< " + s.toString() + " >==<");
        }
    }

    public static void main(String... paramVarArgs) {
        try {
            parsingSendString("start#0a", null);
            return;
        } catch (MacroParsingException localMacroParsingException) {
            localMacroParsingException.printStackTrace();
        }
    }

    private static byte[] makeSendBytes(List<String> paramList, byte[] paramArrayOfByte)
            throws MacroParsingException {
        DumpMsg("makeSendBytes");
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        Iterator localIterator = paramList.iterator();
        for (; ; ) {
            if (!localIterator.hasNext()) {
                if (paramArrayOfByte != null) {
                    localByteArrayOutputStream.write(paramArrayOfByte, 0, paramArrayOfByte.length);
                }
                return localByteArrayOutputStream.toByteArray();
            }
            String str = (String) localIterator.next();
            DumpMsg("makeSendBytes: token = " + str);
            switch (str.charAt(0)) {
                default:
                    byte[] arrayOfByte2 = str.getBytes();
                    localByteArrayOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
                    break;
                case '@':
                    if ((2 == str.length()) && ('@' == str.charAt(1))) {
                        localByteArrayOutputStream.write(64);
                    } else {
                        localByteArrayOutputStream.write(Integer.parseInt(str.substring(1)));
                    }
                    break;
                case '#':
                    if ((2 == str.length()) && ('#' == str.charAt(1))) {
                        localByteArrayOutputStream.write(35);
                    } else {
                        localByteArrayOutputStream.write(fromHex2Decimal(str));
                    }
                    break;
                case '&':
                    if ((2 == str.length()) && ('&' == str.charAt(1))) {
                        localByteArrayOutputStream.write(38);
                    } else {
                        localByteArrayOutputStream.write(fromBin2Decimal(str));
                    }
                    break;
                case '\\':
                    if ((2 == str.length()) && ('\\' == str.charAt(1))) {
                        localByteArrayOutputStream.write(92);
                    } else {
                        byte[] arrayOfByte1 = processSpecialCommand(localByteArrayOutputStream.toByteArray(), str, true, true);
                        localByteArrayOutputStream.write(arrayOfByte1, 0, arrayOfByte1.length);
                    }
                    break;
                case '^':
                    if ((2 == str.length()) && ('^' == str.charAt(1))) {
                        localByteArrayOutputStream.write(94);
                    } else {
                        int i = str.getBytes()[1];
                        int j = (byte) (i - 64);
                        System.out.println("Ctrl: " + i + ", " + str.charAt(1));
                        localByteArrayOutputStream.write(j);
                    }
                    break;
            }
        }
    }

    private static List<String> parsingInputString(String paramString)
            throws MacroParsingException {
        DumpMsg("parsingInputString 1");
        int i = paramString.length();
        Pattern localPattern = Pattern.compile("([^#@&]+)|(##|@@|&&)|(@[0-9]{3})|#\\p{XDigit}{2}|&[01]{8}");
        DumpMsg("parsingInputString 2");
        Matcher localMatcher = localPattern.matcher(paramString);
        DumpMsg("parsingInputString 3");
        int j = 0;
        StringBuilder localStringBuilder1 = new StringBuilder();
        ArrayList localArrayList = new ArrayList();
        if (!localMatcher.find()) {
            if (j != i) {
                localStringBuilder1.append("input string has errors at column ").append(localMatcher.end()).append(": near by '").append(paramString.substring(j, 10)).append("...'\n");
            }
            if (localStringBuilder1.length() != 0) {
                DumpMsg("parsingInputString error " + localStringBuilder1.toString());
                throw new MacroParsingException(localStringBuilder1.toString());
            }
        } else {
            int k = localMatcher.start();
            DumpMsg("start = " + k + ", end = " + j);
            int m = i - j;
            System.out.println("remains = " + m);
            StringBuilder localStringBuilder2 = new StringBuilder();
           /* label289:
            for (int n = j + m; ; n = j + 10) {
                localStringBuilder2.append(paramString.substring(j, n)).append("...'\n");
                j = localMatcher.end();
                localArrayList.add(localMatcher.group());
                break;
            }*/
            for (int n = j + m; ; n = j + 10) {
                if (k != j) {
                    localStringBuilder2 = localStringBuilder1.append("input string has errors at column ").append(j).append(": near by '");
                    if (m >= 10) {
                        break;
                    }
                }
                localStringBuilder2.append(paramString.substring(j, n)).append("...'\n");
                j = localMatcher.end();
                localArrayList.add(localMatcher.group());
                break;
            }

        }
        Iterator localIterator = localArrayList.iterator();
        for (; ; ) {
            if (!localIterator.hasNext()) {
                return localArrayList;
            }
            String str = (String) localIterator.next();
            DumpMsg("token = " + str);
        }
    }

    private static byte[] processSpecialCommand(byte[] paramArrayOfByte, String paramString, boolean paramBoolean1, boolean paramBoolean2) {
        String str = paramString.toLowerCase().substring(1);
        byte[] arrayOfByte = new byte[2];
        if ("n".equals(str)) {
            if ((paramBoolean1) && (paramBoolean2)) {
                arrayOfByte = new byte[2];
                arrayOfByte[0] = 13;
                arrayOfByte[1] = 10;
            }
        }
        for (; ; ) {
            //return arrayOfByte;
            if ((paramBoolean1) && (!paramBoolean2)) {
                return new byte[]{13};
            }
            arrayOfByte = null;
            if (!paramBoolean1) {
                arrayOfByte = null;
                if (paramBoolean2) {
                    //return new byte[]{10};
                    if ("r".equals(str)) {
                        return new byte[]{13};
                    }
                    if ("f".equals(str)) {
                        return new byte[]{10};
                    }
                    if ("t".equals(str)) {
                        return new byte[]{9};
                    }
                    boolean bool = "xor".equals(str);
                    arrayOfByte = null;
                    if (bool) {
                        arrayOfByte = new byte[]{9};
                        int i = paramArrayOfByte.length;
                        for (int j = 0; j < i; j++) {
                            arrayOfByte[0] = ((byte) (paramArrayOfByte[j] ^ arrayOfByte[0]));
                        }
                    }
                    return new byte[]{10};
                }
            }
            return arrayOfByte;
        }
    }

    public static byte[] parsingSendString(String paramString, byte[] paramArrayOfByte)
            throws MacroParsingException {
        return makeSendBytes(parsingInputString(paramString), paramArrayOfByte);
    }

}

/* Location:           C:\Users\Satyam\Desktop\Milk\Compressed\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     com.oneman.freeusbtools.macro.Macro

 * JD-Core Version:    0.7.0.1

 */
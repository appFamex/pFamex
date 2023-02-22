package com.example.drawerfamex;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Environment;

import android.widget.Toast;


import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;



public class documentPDF extends PdfPageEventHelper  {

    public static final String plateia = "res/font/plateiabold.ttf";

    Context context;

    public void documentPDF(Context context){
        this.context = context;
    }


    public static void table (Context context,Document document, int dia, int numColumns, int numRows){
        int [] imgtabla = {R.drawable.barritapdf1, R.drawable.barritapdf2, R.drawable.barritapdf3};
        Image imagetabladia1 = null;
        PdfPTable table = null;
        PdfPCell columntable;
        PdfPCell titulotable;
        PdfPCell imagetabladia;
        BaseColor azul;
        try
        {
            table = new PdfPTable(numColumns);
            table.setWidthPercentage(90);
            azul = new BaseColor(45,46,130);
            imagetabladia = new PdfPCell();


            // IMAGNES DE LA TABLA
            Drawable drawabletabladia1 = context.getDrawable(imgtabla[dia]);
            BitmapDrawable bitDwtabladia1 = ((BitmapDrawable) drawabletabladia1);
            Bitmap bmptabladia1 = bitDwtabladia1.getBitmap();
            ByteArrayOutputStream streamtabladia1 = new ByteArrayOutputStream();
            bmptabladia1.compress(Bitmap.CompressFormat.JPEG, 100, streamtabladia1);
            imagetabladia1 = Image.getInstance(streamtabladia1.toByteArray());
            imagetabladia1.setAlignment(Element.ALIGN_CENTER);
            imagetabladia.setHorizontalAlignment(Element.ALIGN_CENTER);
            imagetabladia.setColspan(3);
            imagetabladia.setImage(imagetabladia1);
            imagetabladia.setBorderColor(azul);

            if(dia == 0){
                imagetabladia.setBorderWidthRight(0.0f);
                imagetabladia.setBorderWidthBottom(5.0f);
                imagetabladia.setBorderWidthTop(5.0f);
                imagetabladia.setBorderWidthLeft(5.0f);
                imagetabladia.setPaddingTop(4.2f);
                imagetabladia.setPaddingBottom(4f);
                imagetabladia.setPaddingLeft(4f);
                imagetabladia.setRight(20.0f);
                imagetabladia.setLeft(20.0f);

            }else{
                if (dia == 1){
                    imagetabladia.setBorderWidthRight(0.0f);
                    imagetabladia.setBorderWidthBottom(5.0f);
                    imagetabladia.setBorderWidthTop(5.0f);
                    imagetabladia.setBorderWidthLeft(0.0f);
                    imagetabladia.setPaddingTop(4.2f);
                    imagetabladia.setPaddingBottom(4f);
                    imagetabladia.setPaddingLeft(4f);

                }else{
                    if (dia == 2){
                        imagetabladia.setBorderWidthRight(5.0f);
                        imagetabladia.setBorderWidthBottom(5.0f);
                        imagetabladia.setBorderWidthTop(5.0f);
                        imagetabladia.setBorderWidthLeft(0.0f);
                        imagetabladia.setPaddingTop(4.2f);
                        imagetabladia.setPaddingBottom(4f);
                        imagetabladia.setPaddingLeft(4f);

                    }
                }
            }
            table.addCell(imagetabladia);


            Phrase titulohorario= new Phrase(String.format("HORARIO"),
                    new Font(Font.FontFamily.HELVETICA, 15,Font.BOLD));
            titulotable = new PdfPCell(new Phrase(titulohorario));
            titulotable.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulotable.setBorderWidthLeft(0.0f);
            titulotable.setBorderWidthTop(0.0f);
            titulotable.setBorderWidthRight(1.0f);
            titulotable.setPadding(9.0f);
            titulotable.setBorderColor(BaseColor.BLACK);
            titulotable.setBorderWidthBottom(3.0f);
            table.addCell(titulotable);


            Phrase titulolugar = new Phrase(String.format("LUGAR"),
                    new Font(Font.FontFamily.HELVETICA, 15,Font.BOLD));
            titulotable = new PdfPCell(new Phrase(titulolugar));
            titulotable.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulotable.setPadding(9.0f);
            titulotable.setBorderColor(BaseColor.BLACK);
            titulotable.setBorderWidthRight(1.0f);
            titulotable.setBorderWidthTop(0.0f);
            titulotable.setBorderWidthBottom(3.0f);
            table.addCell(titulotable);


            Phrase tituloconferencia = new Phrase(String.format("CONFERENCIA"),
                    new Font(Font.FontFamily.HELVETICA, 15,Font.BOLD));
            titulotable = new PdfPCell(new Phrase(tituloconferencia));
            titulotable.setHorizontalAlignment(Element.ALIGN_CENTER);
            titulotable.setPadding(9.0f);
            titulotable.setBorderColor(BaseColor.BLACK);
            titulotable.setBorderWidthRight(1.0f);
            titulotable.setBorderWidthTop(0.0f);
            titulotable.setBorderWidthRight(0.0f);
            titulotable.setBorderWidthBottom(3.0f);
            table.addCell(titulotable);



            int countizq = 1;
            int countder = 3;

            table.setHeaderRows(1);
            for (int row = 1; row<= numRows ; row++){
                for(int column = 1; column <= numColumns; column++){
                    Phrase valores = new Phrase(String.format(row + " hola " + column),
                            new Font(Font.FontFamily.HELVETICA, 12,Font.BOLD));
                    columntable = new PdfPCell(new Phrase(valores));
                    columntable.setHorizontalAlignment(Element.ALIGN_CENTER);
                    columntable.setPadding(13.0f);
                    if(countizq == column){
                        columntable.setBorderWidthLeft(0.0f);
                        columntable.setBorderWidthRight(1.0f);
                    }
                    if(countder == column){
                        columntable.setBorderWidthRight(0.0f);
                        columntable.setBorderWidthLeft(1.0f);
                    }

                    if(numRows == row){
                        columntable.setBorderColor(azul);
                        columntable.setBorderWidthBottom(5.0f);
                    }
                    table.addCell(columntable);
                }
            }
            table.setSpacingAfter(20.0f);
            table.setSpacingBefore(20.0f);
        } catch (BadElementException | IOException e) {
            e.printStackTrace();
        }finally {
            try {
                document.add(table);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }
    }

    public static void createPDF(Context context){
        Document document = new Document(PageSize.LETTER,5,5,130,130);
        String name = "FAMEX.pdf";
        try {
            OutputStream fileOutputStream = new FileOutputStream(createAppDirectory(context));
            PdfWriter writer =PdfWriter.getInstance(document, fileOutputStream);
            Rectangle rectangle = new Rectangle(10, 10, 520, 700);
            writer.setBoxSize("rectangle", rectangle);
            encabezadoPie headerAndFooter = new encabezadoPie(context);
            writer.setPageEvent(headerAndFooter);
            document.open();

            BaseFont baseplateia = BaseFont.createFont(plateia, BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font plateiafont = new Font(baseplateia, 25);

            Paragraph titulouser = new Paragraph(String.format("USERNAME"), plateiafont);
            titulouser.setAlignment(Element.ALIGN_RIGHT);
            titulouser.setIndentationRight(50);
            document.add(titulouser);

            table(context,document,0,3,4);
            table(context,document,1,3,2);
            table(context,document,2,3,5);

            document.addTitle("FAMEX Itinerario");
            document.addSubject("FAMEX");
            document.addCreator("FAMEX");
            document.addAuthor("FAMEX");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }finally {
            document.close();
        }
    }

    public static File createAppDirectory(Context c){
        File file = null;
        String name = "FAMEX.pdf";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            try {
                file = new File(c.getExternalFilesDir(null), name);
                if (!file.exists()) {
                    file.createNewFile();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                file = new File(Environment.getExternalStorageDirectory(),name);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Toast.makeText(c, file.toString(), Toast.LENGTH_SHORT).show();
        return file;
     }
}


class encabezadoPie extends PdfPageEventHelper {
    Context context;
    public static final String plateia = "res/font/plateiabold.ttf";
    public encabezadoPie(Context context) {
        this.context = context;
    }
    public  void onStartPage (PdfWriter writer, Document document) {
        Rectangle rectangle = writer.getBoxSize("rectangle");
        ;
        try
        {
            BaseFont  baseplateia = BaseFont.createFont(plateia, BaseFont.WINANSI, BaseFont.EMBEDDED);
            Font plateiafont = new Font(baseplateia, 30);
            Paragraph titulo1 = new Paragraph(String.format("MI ITINERARIO"), plateiafont);
            titulo1.setAlignment(Element.ALIGN_CENTER);
            titulo1.setIndentationRight(100);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Paragraph(titulo1), rectangle.getRight()-90,
                    rectangle.getTop(), 0);

            //LOGO pFAMEX
            Drawable drawablelogo = context.getDrawable(R.drawable.logopdf);
            BitmapDrawable bitDwlogo = ((BitmapDrawable) drawablelogo);
            Bitmap bmplogo = bitDwlogo.getBitmap();
            ByteArrayOutputStream streamlogo = new ByteArrayOutputStream();
            bmplogo.compress(Bitmap.CompressFormat.PNG, 0, streamlogo);
            Image imagelogo = Image.getInstance(streamlogo.toByteArray());
            imagelogo.setAlignment(Element.ALIGN_CENTER);
            imagelogo.scalePercent(100);
            imagelogo.setAbsolutePosition((
                    rectangle.getLeft() + rectangle.getLeft()+0) , rectangle.getTop()-40);
            PdfContentByte canvas = writer.getDirectContent();
            canvas.addImage(imagelogo);


        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    public void onEndPage (PdfWriter writer, Document document) {
        Rectangle rectangle = writer.getBoxSize("rectangle");
        //PÍE DE PAGINA
        Drawable drawablepie = context.getDrawable(R.drawable.piepaginapdf);
        BitmapDrawable bitDwpie = ((BitmapDrawable) drawablepie);
        Bitmap bmppie = bitDwpie.getBitmap();
        ByteArrayOutputStream streampie = new ByteArrayOutputStream();
        bmppie.compress(Bitmap.CompressFormat.PNG, 25, streampie);
        Image imagelogo = null;
        try {
            imagelogo = Image.getInstance(streampie.toByteArray());
            imagelogo.setAlignment(Element.ALIGN_CENTER);
            imagelogo.setAbsolutePosition((
                    rectangle.getLeft() + rectangle.getLeft()-12), rectangle.getBottom());
            PdfContentByte canvas = writer.getDirectContent();
            canvas.addImage(imagelogo);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}

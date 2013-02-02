//////////////////////////////////////////////////////////////////////////
// Copyright 2001-2013 Aspose Pty Ltd. All Rights Reserved.
//
// This file is part of Aspose.Words. The source code in this file
// is only intended as a supplement to the documentation, and is provided
// "as is", without warranty of any kind, either expressed or implied.
//////////////////////////////////////////////////////////////////////////
//14/9/06 by Vladimir Averkin
package Word2Help;

import java.io.File;
import java.net.URI;
import java.text.MessageFormat;


/**
 * This project converts documentation stored inside a DOC format to a series of HTML documents. This output is in
 * a form that can then be easily compiled together into a single compiled help file (CHM) by using
 * the Microsoft HTML Help Workshop application.
 */
public class Program
{
    public static void main(String[] args) throws Exception
    {
        URI exeDir = Program.class.getResource("").toURI();
        // Specifies the source directory, processes all *.doc files found in it.
        String dataDir = new File(exeDir.resolve("../../Data")) + File.separator;
        // Specifies the destination directory where the HTML files are output.
        File outPath = new File(dataDir, "Out");

        // Remove any existing output and recreate the Out folder.
        if(outPath.exists())
        {
            for(File file : outPath.listFiles())
            {
                file.delete();
            }
        }

        outPath.mkdirs();
        String outDir = outPath.getAbsolutePath();

        // Specifies the part of the URLs to remove. If there are any hyperlinks that start
        // with the above URL, this URL is removed. This allows the document designer to include
        // links to the HTML API and they will be "corrected" so they work both in the online
        // HTML and also in the compiled CHM.
        String fixUrl = "";

        // *** LICENSING ***
        // An Aspose.Words license is required to use this project fully.
        // Without a license Aspose.Words will work in evaluation mode and truncate documents
        // and output watermarks.
        //
        // You can download a free 30-day trial license from the Aspose site. The easiest way is to set the license is to
        // include the license in the executing directory and uncomment the following code.
        //
        // Aspose.Words.License license = new Aspose.Words.License();
        // license.setLicense("Aspose.Words.lic");
        System.out.println(MessageFormat.format("Extracting topics from {0}.", dataDir));

        TopicCollection topics = new TopicCollection(dataDir, fixUrl);
        topics.addFromDir(dataDir);
        topics.writeHtml(outDir);
        topics.writeContentXml(outDir);

        System.out.println("Conversion completed successfully.");
    }
}


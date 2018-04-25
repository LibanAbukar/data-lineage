import tracedatalineage.model.Column
import tracedatalineage.model.SqlFileList
import tracedatalineage.model.traceDataLineage

class StartDataLineage {

    static void main( String[] args )
    {
        if ( args.length == 0 )
        {
            System.out.println( "Usage: java traceDataLineage <sql scripts directory path> <output file path>" )
            System.out.println( "sql scripts directory path: The sql files directory will be analyzed." )
            System.out.println( "output file path: Option, write the analysis result to the specified file." )
            return
        }

        String outputFile = null
        Writer writer = null
        String ddlPath = null
        if ( args.length == 1 )
        {
            ddlPath = args[0]
        }
        else if ( args.length >= 2 )
        {
            ddlPath = args[0]
            outputFile = args[1]
        }
        try
        {
            if ( outputFile != null )
            {
                File file = new File( outputFile )
                if ( file.exists( ) )
                    file.delete( )
                writer = new FileWriter( outputFile, true )
            }
            else
            {
                writer = new StringWriter( )
            }

            SqlFileList sqlFileList = new SqlFileList( ddlPath )
            List<String> sqlFiles = sqlFileList.sqlfiles
            List<InputStream> streams = new ArrayList<InputStream>( )
            for ( int i = 0; i < sqlFiles.size( ); i++ )
            {
                streams.add( new FileInputStream( sqlFiles.get( i ) ) )
            }

            def trace = new traceDataLineage( streams )
            println(trace.getTracedLineage().toString())

            
        }
        catch ( Exception e )
        {
            e.printStackTrace( );
        }
    }
}


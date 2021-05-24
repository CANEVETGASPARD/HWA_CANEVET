import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class JoinMapper extends Mapper<LongWritable, Text, IntWritable, GenericCustomerEntity>{

    @Override
    protected void map(LongWritable key,
                       Text value,
                       Mapper<LongWritable, Text, IntWritable, GenericCustomerEntity>.Context context) throws IOException, InterruptedException {

        String[] tuple = value.toString().split("\\|");
        AbstractCustomerEntity v;

        if (tuple.length >= 9) { // football match
            v = new Orders(Integer.parseInt(tuple[0]),
                    tuple[1],
                    tuple[2],
                    tuple[3],
                    tuple[4],
                    tuple[5],
                    tuple[6],
                    tuple[7],
                    tuple[8]);
        } else { // football location
            v = new Customer(Integer.parseInt(tuple[0]),
                    tuple[1],
                    tuple[2],
                    tuple[3],
                    tuple[4],
                    tuple[5],
                    tuple[6],
                    tuple[7]);
        }
        //System.out.println(v.toString());
        context.write(new IntWritable(Integer.parseInt(tuple[0])), new GenericCustomerEntity(v));
    }

}
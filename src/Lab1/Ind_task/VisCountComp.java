package Lab1.Ind_task;

import java.util.Comparator;

public class VisCountComp implements Comparator<AbsRecept>{
        @Override
        public int compare(AbsRecept o1, AbsRecept o2) {
            return o1.getCount() - o2.getCount();
        }
}

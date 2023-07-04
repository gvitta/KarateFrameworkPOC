import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRunner {

    @Test
    public void executeKarateTests() {
        Runner.Builder aRunner = new Runner.Builder();
        aRunner.path("classpath:/com/ps/test/features");
        aRunner.tags("~@ignore");
        aRunner.outputCucumberJson(true);
        aRunner.reportDir("build/karate-reports");
        Results results = aRunner.parallel(0);
        generateReport(results.getReportDir());
        assertTrue(results.getFailCount() == 0, results.getErrorMessages());
    }

    public static void generateReport(String karateOutputPath) {
        File reportDir = new File(karateOutputPath);
        Collection<File> jsonFiles = FileUtils.listFiles(reportDir, new String[]{"json"}, true);
        List<String> jsonPaths = new ArrayList<>(jsonFiles.size());
        jsonFiles.forEach(file -> jsonPaths.add(file.getAbsolutePath()));
        Configuration config = new Configuration(reportDir, "Sample-Test-Framework-Report");
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);
        reportBuilder.generateReports();

    }

    @AfterAll
    public static void closeAllBrowsers(){

    }
}

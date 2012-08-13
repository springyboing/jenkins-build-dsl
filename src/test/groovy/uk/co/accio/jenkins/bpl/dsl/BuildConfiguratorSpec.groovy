package uk.co.accio.jenkins.bpl.dsl

import groovy.xml.StreamingMarkupBuilder
import groovy.xml.XmlUtil
import org.custommonkey.xmlunit.Diff
import org.custommonkey.xmlunit.XMLUnit
import uk.co.accio.jenkins.dsl.BuildJobDelegate
import uk.co.accio.jenkins.dsl.BuildConfigurator
import spock.lang.Specification

class BuildConfiguratorSpec extends Specification {

    def 'Simple single buildJob DSL'() {

        given:
            def buildConfigurator = new BuildConfigurator()
            def theDSL = '''\
                builds {
                    buildJob('MyFirstBuild') {
                    }
                }
                '''.stripIndent()

        when:
            buildConfigurator.runJenkinsBuilder(theDSL)

        then:
            buildConfigurator.buildConfig.buildJobs[0].name == 'MyFirstBuild'
    }

    def 'Simple single buildJob DSL using appName variable'() {

            given:
                def buildConfigurator = new BuildConfigurator()
                buildConfigurator.bindVariable('appName', 'donkey')
                def theDSL = '''\
                    builds {
                        buildJob("${appName}") {
                        }
                    }
                    '''.stripIndent()

            when:
                buildConfigurator.runJenkinsBuilder(theDSL)

            then:
                buildConfigurator.buildConfig.buildJobs[0].name == 'donkey'
        }

    
}

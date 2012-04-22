import org.cloudifysource.dsl.context.ServiceContextFactory
import org.cloudifysource.dsl.utils.ServiceUtils;

config = new ConfigSlurper().parse(new File("mongoConfig-service.properties").toURL())
osConfig = ServiceUtils.isWindows() ? config.win32 : config.unix

serviceContext = ServiceContextFactory.getServiceContext()
instanceID = serviceContext.getInstanceId()
println "mongoConfig_install.groovy: Writing mongoConfig port to this instance(${instanceID}) attributes..."

home = "${serviceContext.serviceDirectory}/mongodb-${config.version}"
script = "${home}/bin/mongod"

serviceContext.attributes.thisInstance["home"] = "${home}"
serviceContext.attributes.thisInstance["script"] = "${script}"
println "mongoConfig_install.groovy: mongoConfig(${instanceID}) home is ${home}"


serviceContext.attributes.thisInstance["port"]=config.port
port=serviceContext.attributes.thisInstance["port"]
println "mongoConfig_install.groovy: mongoConfig(${instanceID}) is using port ${port}"

builder = new AntBuilder()
builder.sequential {
	mkdir(dir:"${config.installDir}")
	get(src:"${osConfig.downloadPath}", dest:"${config.installDir}/${osConfig.zipName}", skipexisting:true)
}

if(ServiceUtils.isWindows()) {
	builder.unzip(src:"${config.installDir}/${osConfig.zipName}", dest:"${config.installDir}", overwrite:true)
} else {
	builder.untar(src:"${config.installDir}/${osConfig.zipName}", dest:"${config.installDir}", compression:"gzip", overwrite:true)
}
builder.move(file:"${config.installDir}/${osConfig.name}", tofile:"${home}")

if(!ServiceUtils.isWindows()) {
	println "calling chmod on ${home}/bin"
	builder.chmod(dir:"${home}/bin", perm:'+x', includes:"*")
}

println "mongoConfig_install.groovy: Installation of mongoConfig(${instanceID}) ended"
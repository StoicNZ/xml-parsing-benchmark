file = new File("nvdcve-2.0-2011.xml")
start = new Date()
xml = new XmlSlurper().parse(file)
out = []
xml.entry.each() { it ->
    kv = [:]
    it.children().each() { element ->
        switch(element.name()){
            case "cve-id":
                kv.put("CVE", element.text()); break;
            case "summary":
                kv.put("Summary", element.text()); break;
            case "reference":
                kv.put("Reference", element.text()); break;
            case "published-datetime":
                kv.put("Date", element.text()); break;
        }
    }
    out.add(kv)
}
//println out[4000]

println((new Date().getTime() - start.getTime())/1000.0)
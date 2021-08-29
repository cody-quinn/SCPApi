package me.codyq.scpapi

import me.codyq.scpapi.models.SCP
import me.codyq.scpapi.models.SCPImage
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions


fun genSCP(id: String): SCP{
    val url = "https://scp-wiki.wikidot.com/scp-$id"
    val options = ChromeOptions()
    options.addArguments("--headless")
    val driver = ChromeDriver(options)
    driver.get(url)

    val page = driver.findElementById("page-content").findElements(By.tagName("p")).map { it.text }
    val caption = driver.findElementsByClassName("scp-image-caption").map { it.text }
    val rating = driver.findElementByClassName("prw54353").text.toInt()
    val images = driver.findElementsByClassName("scp-image-block").map{
        it.findElement(By.className("image")).getAttribute("src")
    }
    val img: ArrayList<SCPImage> = ArrayList()

    for (i in images){
        val index = images.indexOf(i)
        val image = SCPImage(images[index], caption[index])
        img.add(image)
    }


    var body = ""
    var objectClass = ""
    var itemNumber = id
    var containment = ""
    var desc = ""
    for (element in page){
        try {
            if (element[0] == '«' || element in caption){
                continue
            } else {
                if (element.startsWith("Object Class: ")){
                    objectClass = element.removePrefix("Object Class: ")
                } else if (element.startsWith("Item #: ")){
                    itemNumber = element.removePrefix("Item #: ")
                } else if (element.startsWith("Special Containment Procedures: ")){
                    containment = element.removePrefix("Special Containment Procedures: ")
                } else if (element.startsWith("Description: ")){
                    desc = element.removePrefix("Description: ")
                } else {
                    body += element
                }

            }

        } catch (e: Exception){
            continue
        }

    }

    return SCP(rating, itemNumber, objectClass, containment, desc, body, img)

}


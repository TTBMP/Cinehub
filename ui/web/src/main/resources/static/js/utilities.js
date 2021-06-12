function getTodayDate() {
    return new Date().toISOString().split('T')[0]
}

async function replace(tag, source) {
    const element = this.document.getElementsByTagName(tag)[0];
    element.innerHTML=`<object type="text/html" data="${source}"></object>`
    await new Promise(resolve => setTimeout(resolve, 500));  // TODO: remove and use a promise to get the document
    const sourceDocument = await (element.getElementsByTagName("object")[0]).contentDocument
    element.outerHTML=`${sourceDocument.getElementsByTagName(tag)[0].outerHTML}`;
}

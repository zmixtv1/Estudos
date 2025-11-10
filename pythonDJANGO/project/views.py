from django.http import HttpResponse

def teste_view(request):
    return HttpResponse("<h1>Testando!</h1>")

def index_view(request):
    return HttpResponse("Olá mundo!")
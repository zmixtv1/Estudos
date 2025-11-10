from django.shortcuts import render
from django.http import HttpResponse

# Create your views here.
def tarefas_home(requests):
    return HttpResponse("Conseguiu fazer outra rota!")
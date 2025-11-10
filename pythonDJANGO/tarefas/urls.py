from django.urls import path
from django.http import HttpResponse
from django.shortcuts import render
from . import views

urlpatterns = [
    path('', views.tarefas_home)
]
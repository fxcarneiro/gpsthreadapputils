// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.


package com.ufla.gpsthreadapputils.utils;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Classe de utilidade para acesso a permissões em tempo de execução.
 */
public abstract class PermissionUtils {

    /**
     * Um diálogo que exibe uma mensagem de permissão negada.
     */
    public static class PermissionDeniedDialog extends DialogFragment {

        private static final String ARGUMENT_FINISH_ACTIVITY = "finish";

        // Indica se a atividade que chamou o diálogo deve ser finalizada após fechar o diálogo.
        private boolean finishActivity = false;

        /**
         * Cria uma nova instância deste diálogo e, opcionalmente, finaliza a atividade que o chamou
         * quando o botão 'Ok' é clicado.
         *
         * @param finishActivity Se verdadeiro, finaliza a atividade ao fechar o diálogo.
         * @return Uma nova instância de PermissionDeniedDialog configurada com os argumentos fornecidos.
         */
        public static PermissionDeniedDialog newInstance(boolean finishActivity) {
            Bundle arguments = new Bundle();
            arguments.putBoolean(ARGUMENT_FINISH_ACTIVITY, finishActivity);

            PermissionDeniedDialog dialog = new PermissionDeniedDialog();
            dialog.setArguments(arguments); // Define os argumentos para controlar o comportamento ao fechar o diálogo.
            return dialog; // Retorna a instância do diálogo criada.
        }
//
        // Aqui, você implementaria métodos adicionais para configurar o diálogo,
        // como onCreateDialog, para criar a interface do diálogo com o usuário.
    }
}


